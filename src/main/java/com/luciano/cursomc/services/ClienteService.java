package com.luciano.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luciano.cursomc.domain.Cidade;
import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.domain.Endereco;
import com.luciano.cursomc.domain.enums.Perfil;
import com.luciano.cursomc.domain.enums.TipoCliente;
import com.luciano.cursomc.dto.ClientDTOEndereco;
import com.luciano.cursomc.dto.ClienteDTO;
import com.luciano.cursomc.repositories.ClienteRepository;
import com.luciano.cursomc.repositories.EnderecoRepository;
import com.luciano.cursomc.security.UserSS;
import com.luciano.cursomc.services.exceptions.AuthorizationException;
import com.luciano.cursomc.services.exceptions.DataIntegrityException;
import com.luciano.cursomc.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private BCryptPasswordEncoder password;

	private UserSS user;
		
	public Cliente find(Integer id) {
		
		user = UserService.authenticated();
		
		
		if(user == null || !user.hasRole(Perfil.ADMIN)	&& !id.equals(user.getId())){
			throw new AuthorizationException("Acesso negado");
		}
				
		
		Optional<Cliente> c = clienteRepository.findById(id);
		return c.orElseThrow(() -> new ObjectNotFoundException("Cliente não encontrado! Id: " +id+ ", Tipo: " +Cliente.class.getName()));
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
		return new Cliente(clienteDTO.getId(), clienteDTO.getNome(), clienteDTO.getEmail(), null , null, null);
	}
	
	public Cliente fromDTO(ClientDTOEndereco clienteDTOEndereco) {
		Cliente c = new Cliente(null, clienteDTOEndereco.getNome(), clienteDTOEndereco.getEmail(), clienteDTOEndereco.getCpfOuCnpj() , TipoCliente.toEnum(clienteDTOEndereco.getTipo()), password.encode(clienteDTOEndereco.getSenha()));
		Cidade cidade = new Cidade(clienteDTOEndereco.getCidadeId(), null, null);	
		Endereco end = new Endereco(null, clienteDTOEndereco.getLogradouro(), clienteDTOEndereco.getNumero(), clienteDTOEndereco.getComplemento(), clienteDTOEndereco.getBairro(), clienteDTOEndereco.getCep(), c, cidade);
		c.getEnderecos().add(end);
		c.getTelefones().add(clienteDTOEndereco.getTelefone1());
		if(clienteDTOEndereco.getTelefone2()!=null) {
			c.getTelefones().add(clienteDTOEndereco.getTelefone2());
		}
		if(clienteDTOEndereco.getTelefone3()!=null) {
			c.getTelefones().add(clienteDTOEndereco.getTelefone3());
		}
		return c;
	
	}
	
	private void updateData(Cliente newCliente, Cliente oldCliente) {
		newCliente.setNome(oldCliente.getNome());
		newCliente.setEmail(oldCliente.getEmail());
	}
	
	@Transactional(readOnly=true)
	public Cliente insert(Cliente cliente) {
		//enderecoRepository.saveAll(cliente.getEnderecos());
		return clienteRepository.save(cliente);
	}
	
	


}
