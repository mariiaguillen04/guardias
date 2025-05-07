package es.KioskTV.serviceImpl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import es.KioskTV.Repository.UserRepository;
import es.KioskTV.entity.User;

/**
 * Implementation of UserDetailsService interface.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	/**
     * Loads a user by their DAS (unique identifier) and returns the corresponding UserDetails.
     * This method is used during authentication to retrieve user details.
     *
     * @param das The unique identifier (DAS) of the user.
     * @return UserDetails The user details retrieved from the database.
     * @throws NoSuchElementException if no user is found with the given DAS.
     */
	@Override
	public UserDetails loadUserByUsername(String das) throws NoSuchElementException {
		User user = userRepository.findByDas(das).orElseThrow();
		return user;
	}
}