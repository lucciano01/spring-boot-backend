package com.luciano.cursomc.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.luciano.cursomc.domain.Categoria;
import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.dto.CategoriaDTO;
import com.luciano.cursomc.dto.ClientDTOEndereco;
import com.luciano.cursomc.dto.ClienteDTO;
import com.luciano.cursomc.repositories.EnderecoRepository;
import com.luciano.cursomc.services.ClienteService;

@RestController
@RequestMapping(value= "/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	
	
	@RequestMapping(value= "/{id}" , method= RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable Integer id){
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientDTOEndereco clienteDTOEndereco){
	//	
		Cliente cliente = clienteService.fromDTO(clienteDTOEndereco);
		cliente = clienteService.insert(cliente);
		//retorno da uri do recurso de criação essa é uma boa prática
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(cliente.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}" , method= RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO ClienteDTO, @PathVariable Integer id){
		Cliente Cliente = clienteService.fromDTO(ClienteDTO);
		Cliente.setId(id);
		clienteService.update(Cliente);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value= "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable Integer id){
		clienteService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/listar" , method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll(){
		List<Cliente> Clientes = clienteService.findAll();
		List<ClienteDTO> ClientesDTO = Clientes.stream().map(cat -> new ClienteDTO(cat)).collect(Collectors.toList());
		return ResponseEntity.ok().body(ClientesDTO);
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/page" , method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			@RequestParam(name="page" , defaultValue="0") Integer page, 
			@RequestParam(name="linesPerPage", defaultValue= "24")Integer linesPerPage, 
			@RequestParam(name="orderBy" , defaultValue= "nome") String orderBy, 
			@RequestParam(name="direction" , defaultValue="ASC") String direction){
		Page<Cliente> Clientes = clienteService.findPage(page, linesPerPage, direction, orderBy);
		Page<ClienteDTO> ClientesDTO = Clientes.map(cat -> new ClienteDTO(cat));
		return ResponseEntity.ok().body(ClientesDTO);
	}

}
