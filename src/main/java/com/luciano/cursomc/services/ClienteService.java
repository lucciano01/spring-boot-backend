package com.luciano.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.dto.ClienteDTO;
import com.luciano.cursomc.repositories.ClienteRepository;
import com.luciano.cursomc.services.exceptions.DataIntegrityException;
import com.luciano.cursomc.services.exceptions.ObjectNotFoundExeception;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Cliente find(Integer id) {
		Optional<Cliente> c = clienteRepository.findById(id);
		return c.orElseThrow(() -> new ObjectNotFoundExeception("Cliente não encontrado! Id: " +id+ ", Tipo: " +Cliente.class.getName()));
	}
	
	public Cliente update(Cliente cliente) {
		Cliente newCliente = find(cliente.getId());
		updateData(newCliente, cliente);
		return clienteRepository.save(newCliente);
	}

	public void delete(Integer id) {
		find(id);
		try {
			clienteRepository.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas");
		}

	}
	
	public List<Cliente> findAll(){
		return clienteRepository.findAll();
	}
	
	//serviço de paginação
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String direction, String orderBy){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);
	
	}
	
	public Cliente fromDTO(ClienteDTO clienteDTO) {
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null , null);
	}
	
	private void updateData(Cliente newCliente, Cliente oldCliente) {
		newCliente.setNome(oldCliente.getNome());
		newCliente.setEmail(oldCliente.getEmail());
	}


}
