package com.luciano.cursomc.services.validation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.dto.ClienteDTO;
import com.luciano.cursomc.repositories.ClienteRepository;
import com.luciano.cursomc.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClientUpdate, ClienteDTO>{

	private List<FieldMessage> lista = new ArrayList<FieldMessage>();
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	HttpServletRequest request;
	
	@Override
	public void initialize(ClientUpdate constraintAnnotation) {
		
	}
	
	
	@Override
	public boolean isValid(ClienteDTO clientDTOEndereco, ConstraintValidatorContext context) {
		
		Map<String,String> map = new HashMap<>();
		map = (Map<String,String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer id = Integer.parseInt(map.get("id"));
		
		Cliente aux = clienteRepository.findByEmail(clientDTOEndereco.getEmail());
		if(aux!=null && !aux.getId().equals(id)) {
			lista.add(new FieldMessage("email", "Email já existente na base de dados"));
		}
		
		
		for(FieldMessage field : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(field.getMessage())
			.addPropertyNode(field.getFileName())
			.addConstraintViolation();
		}
		
		return lista.isEmpty();
	}
	
	
	
	

	
}
