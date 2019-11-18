package com.luciano.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.services.validation.ClientUpdate;

@ClientUpdate
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	@NotEmpty(message= "Preenchimento obrigatório")
	@Length(min=3, max=120, message = "O tamanho deve ter entre 5 a 120 caracteres")
	private String nome;
	
	@NotEmpty(message= "Preenchimento obrigatório")
	@Email(message="Email inválido!")
	private String email;
	
	@NotEmpty(message = "Campo obrigatório")
	private String senha;
	
	public ClienteDTO() {
		
	}
	
	public ClienteDTO(Cliente cliente) {
		id = cliente.getId();
		nome = cliente.getNome();
		email = cliente.getEmail();
	}
		
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	
	
	
	
	

}
