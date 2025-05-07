package com.guardias.service;

import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

import com.guardias.persintence.entity.Usuario;
import com.guardias.persintence.repository.UsuarioRepository;
import com.guardias.service.request.SigninRequest;
import org.springframework.stereotype.Service;

import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import org.springframework.util.StreamUtils;
import java.io.IOException;

/**
 * Implementation of AuthenticationService interface.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private UsuarioRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;
    private final Map<String, String> resetCodes = new ConcurrentHashMap<>();

    public AuthenticationServiceImpl(UsuarioRepository userRepository,
            @Lazy PasswordEncoder passwordEncoder,
            JwtServiceImpl jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    /**
     * Registers a new user in the system.
     * If the provided "DAS" (unique identifier) already exists, an exception is
     * thrown.
     *
     * @param request The request object containing user registration details.
     * @return A JwtAuthenticationResponse with the user's role (no token is created
     *         during signup).
     */
    @Override
    public JwtAuthenticationResponse signup(SignUpRequest request) {
        if (userRepository.findByDas(request.getDas()).isPresent()) {
            throw new IllegalArgumentException("El das ya existe.");
        }

        User user = new User();
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setDas(request.getDas());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        userRepository.save(user);

        try {
            sendAccountCreatedEmail(user);
        } catch (EmailConnectionException e) {
            System.err.println("Failed to send welcome email: " + e.getMessage());
        }

        return new JwtAuthenticationResponse(null, Collections.singleton(user.getRole().name()));
    }

    /**
     * Authenticates a user and generates a JWT token upon successful login.
     *
     * @param request The request object containing login credentials.
     * @return A JwtAuthenticationResponse with a generated token and user roles.
     */
    @Override
    public JwtAuthenticationResponse signin(SigninRequest request) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getDas(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Usuario user = userRepository.findById(request.getDas())
                .orElseThrow(() -> new IllegalArgumentException("Invalid das or password."));

        String jwt = jwtService.generateToken(user);
        Set<String> roles = user.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        return JwtAuthenticationResponse.builder()
                .token(jwt)
                .roles(roles)
                .build();
    }

    /**
     * Sends a password reset email to the user.
     * This method generates a reset link and sends it via email using a predefined
     * HTML template.
     *
     * @param das The DAS of the user requesting the password reset.
     * @throws EmailConnectionException if the email fails to send due to connection
     *                                  issues.
     */
    @Override
    public void sendPasswordChangeEmail(String das) throws EmailConnectionException {
        Usuario user = userRepository.findByDas(das).orElse(null);
        if (user == null) {
            throw new IllegalArgumentException("DAS NOT FOUND");
        }

        // Generate random code
        String resetCode = UUID.randomUUID().toString();
        resetCodes.put(das.toLowerCase(), resetCode);

        String htmlContent = loadHtmlTemplate("templates/password-reset-link.html");

        // Generate the reset link with code
        String resetLink = "http://localhost:4200/reset-password?das=" + user.getDas() + "&code=" + resetCode;

        htmlContent = htmlContent.replace("{{FIRST_NAME}}", user.getFirstName())
                .replace("{{RESET_LINK}}", resetLink);

        // Email configuration
        String to = user.getEmail();
        final String from = "no.reply.kiosktv@gmail.com";
        final String password = "clut ougu kuvj mxjw";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Reset Your Password");
            message.setContent(htmlContent, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Password reset email sent to: " + to);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new EmailConnectionException("Connection failed while trying to send the email.");
        }
    }

    /**
     * Resets the password of the user identified by DAS.
     * This method updates the user's password in the database with a newly encoded
     * one.
     *
     * @param das         The DAS of the user whose password is being reset.
     * @param newPassword The new password to be set for the user.
     */
    @Override
    public void resetPassword(String das, String code, String newPassword) {
        String storedCode = resetCodes.get(das.toLowerCase());

        if (storedCode == null || !storedCode.equals(code)) {
            throw new IllegalArgumentException("Invalid or expired reset code.");
        }

        User user = userRepository.findByDas(das)
                .orElseThrow(() -> new IllegalArgumentException("User not found with DAS: " + das));

        user.setPassword(passwordEncoder.encode(newPassword));
        userRepository.save(user);

        // Invalidate code after successful password reset
        resetCodes.remove(das.toLowerCase());
    }

    /**
     * Sends a welcome email after user account creation.
     *
     * @param user The newly created user.
     * @throws EmailConnectionException if the email fails to send.
     */
    public void sendAccountCreatedEmail(User user) throws EmailConnectionException {
        // Load the welcome HTML email template
        String htmlContent = loadHtmlTemplate("templates/welcome-email.html");

        // Replace placeholders with user details
        htmlContent = htmlContent.replace("{{FIRST_NAME}}", user.getFirstName())
                .replace("{{DAS}}", user.getDas());

        // Email configuration
        String to = user.getEmail();
        final String from = "no.reply.kiosktv@gmail.com";
        final String password = "clut ougu kuvj mxjw";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new javax.mail.PasswordAuthentication(from, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject("Welcome to Kiosk-TV!");
            message.setContent(htmlContent, "text/html; charset=utf-8");

            Transport.send(message);
            System.out.println("Welcome email sent to: " + to);

        } catch (MessagingException e) {
            e.printStackTrace();
            throw new EmailConnectionException("Connection failed while trying to send welcome email.");
        }
    }

    /**
     * Loads an HTML email template from the specified path.
     *
     * @param path The file path of the template.
     * @return The content of the HTML file as a string.
     */
    private String loadHtmlTemplate(String path) {
        try {
            ClassPathResource resource = new ClassPathResource(path);
            return StreamUtils.copyToString(resource.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException("Error loading email template", e);
        }
    }
}

