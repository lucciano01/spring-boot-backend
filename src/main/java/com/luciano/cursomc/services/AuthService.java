package com.luciano.cursomc.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.repositories.ClienteRepository;
import com.luciano.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private IEmail emailService;
	
	private Random random = new Random();
	
	public void sendNewPassword(String email) {
		Cliente cliente = clienteRepository.findByEmail(email);
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado");
		}
		
		String newPassword = generatedNewPassword();
		cliente.setSenha(passwordEncoder.encode(newPassword));
		
		clienteRepository.save(cliente);
		emailService.sendNewPasswordEmail(cliente, newPassword);
		
	}

	private String generatedNewPassword() {
		char [] vet = new char[10];
		for(int i = 0; i < vet.length; i++) {
			vet[i] =  randomChar();
		}
		return new String(vet);
	}

	//os valores dos parametros do random 
	// são retirados da tabela unicode-table
	private char randomChar() {
		int opt = random.nextInt(3);
		if(opt == 0) { //gera um digito
			return (char) (random.nextInt(10) + 48);
		}else if(opt == 1) { //gera letra maiscula
			return (char) (random.nextInt(26) + 65);
		}else { //gera letra minuscula
			return (char) (random.nextInt(26) + 97);
		}
	}

}
