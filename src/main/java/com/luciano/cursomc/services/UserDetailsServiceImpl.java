package com.luciano.cursomc.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.repositories.ClienteRepository;
import com.luciano.cursomc.security.UserSS;

public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repository;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = repository.findByEmail(email);
		if(cliente == null) {
			throw new UsernameNotFoundException(email);
		}
		return new UserSS(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
