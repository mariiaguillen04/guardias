package com.guardias.web.controllers;

import java.util.Map;
import java.util.Optional;

import com.guardias.persintence.repository.UsuarioRepository;
import com.guardias.service.AuthenticationServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * This class is a REST controller for handling authentication requests.
 * It contains endpoints for user sign-up and sign-in operations.
 */
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    private UsuarioRepository userRepository;

    /**
     * Constructs an AuthenticationController with the specified
     * AuthenticationService.
     *
     * @param authenticationService the service used for authentication operations
     */
    @Autowired
    public AuthenticationController(AuthenticationServiceImpl authenticationService) {
        this.authenticationService = authenticationService;
    }

    /**
     * Handles the sign-up request. Creates a new user account.
     *
     * @param request the sign-up request containing user details
     * @return a ResponseEntity containing a JwtAuthenticationResponse
     */
    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponse> signup(@RequestBody SignUpRequest request) {
        String comprobarDas = request.getDas();

        Optional<User> usuarioBuscar = userRepository.findByDas(comprobarDas);

        if (usuarioBuscar.isPresent()) {
            throw new RuntimeException("A user with DAS " + comprobarDas + " already exists.");
        }

        JwtAuthenticationResponse response = authenticationService.signup(request);

        return ResponseEntity.ok(response);
    }

    /**
     * Handles the sign-in request. Authenticates the user.
     *
     * @param request the sign-in request containing user credentials
     * @return a ResponseEntity containing a JwtAuthenticationResponse
     */
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signin(@RequestBody SigninRequest request) {
        JwtAuthenticationResponse response = authenticationService.signin(request);
        return ResponseEntity.ok(response);
    }

    /**
     * Handles the request for sending a password change email.
     * Sends an email to the user to allow them to change their password.
     *
     * @param request a map containing the DAS of the user requesting the password
     *                change
     * @return a ResponseEntity containing a success or error message
     */
    @PostMapping("/send-password-email")
    public ResponseEntity<String> sendPasswordChangeEmail(@RequestBody Map<String, String> request) {
        String das = request.get("das");

        if (das == null || das.isEmpty()) {
            return ResponseEntity.badRequest().body("DAS is required");
        }

        try {
            authenticationService.sendPasswordChangeEmail(das.toLowerCase());
            return ResponseEntity.ok("Email sent successfully");
        } catch (EmailConnectionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email due to connection issues.");
        }
    }

     /**
     * Handles the request for resetting the user's password.
     * Accepts the user's DAS and the new password, then updates it in the database.
     *
     * @param request a map containing the user's DAS and the new password
     * @return a ResponseEntity indicating success or error
     */
    @PostMapping("/reset-password")
    public ResponseEntity<String> resetPassword(@RequestBody Map<String, String> request) {
        String das = request.get("das");
        String code = request.get("code"); 
        String newPassword = request.get("newPassword");
    
        if (das == null || newPassword == null || code == null || das.isEmpty() || code.isEmpty() || newPassword.isEmpty()) {
            return ResponseEntity.badRequest().body("DAS, code and new password are required.");
        }
    
        try {
            authenticationService.resetPassword(das.toLowerCase(), code, newPassword); 
            return ResponseEntity.ok("Password reset successfully.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while resetting the password.");
        }
    }
    
}
