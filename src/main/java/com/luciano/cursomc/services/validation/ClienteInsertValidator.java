package com.luciano.cursomc.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.luciano.cursomc.domain.enums.TipoCliente;
import com.luciano.cursomc.dto.ClientDTOEndereco;
import com.luciano.cursomc.repositories.ClienteRepository;
import com.luciano.cursomc.resources.exceptions.FieldMessage;
import com.luciano.cursomc.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClientInsert, ClientDTOEndereco>{

	private List<FieldMessage> lista = new ArrayList<FieldMessage>();
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public void initialize(ClientInsert constraintAnnotation) {
		
	}
	
	
	@Override
	public boolean isValid(ClientDTOEndereco clientDTOEndereco, ConstraintValidatorContext context) {
		
		if(clientDTOEndereco.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo())
				&& (!BR.isValidCPF(clientDTOEndereco.getCpfOuCnpj()))) {
			lista.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
		}
		
		if(clientDTOEndereco.getTipo().equals(TipoCliente.PESSOA_JURIDICA.getCodigo())
				&& (!BR.isValidCNPJ(clientDTOEndereco.getCpfOuCnpj()))) {
			lista.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
		}
		
		if(clienteRepository.findByEmail(clientDTOEndereco.getEmail())!=null) {
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
	
	public boolean validaCpf(ClientDTOEndereco clientDTOEndereco) {
		return clientDTOEndereco.getTipo().equals(TipoCliente.PESSOA_FISICA.getCodigo())
				&& (!BR.isValidCPF(clientDTOEndereco.getCpfOuCnpj()));
	}
	
	public void validaCnpj(ClientDTOEndereco clientDTOEndereco) {
		
	}
	
	

	
}
