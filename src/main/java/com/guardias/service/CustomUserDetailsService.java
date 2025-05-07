package com.guardias.service;

import java.util.NoSuchElementException;

import com.guardias.persintence.entity.Usuario;
import com.guardias.persintence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/**
 * Implementation of UserDetailsService interface.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UsuarioRepository userRepository;

	public UserDetails loadUserByUsername(int idUsuario) throws NoSuchElementException {
		Usuario user = userRepository.findById(idUsuario).orElseThrow();
		return (UserDetails) user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return null;
	}
}