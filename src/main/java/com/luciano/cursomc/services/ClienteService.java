package com.luciano.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.repositories.ClienteRepository;
import com.luciano.cursomc.services.exceptions.ObjectNotFoundExeception;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> c = clienteRepository.findById(id);
		return c.orElseThrow(() -> new ObjectNotFoundExeception("Cliente não encontrado! Id: " +id+ ", Tipo: " +Cliente.class.getName()));
	}
	

}
