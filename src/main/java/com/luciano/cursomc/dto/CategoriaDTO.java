package com.luciano.cursomc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.luciano.cursomc.domain.Categoria;

public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "Campo obrigatório!")
	@Length(min=5 , max=80 , message= "Tamanho deve estar entre 5 e 80 caracteres")
	private String nome;
	
	public CategoriaDTO() {
		
	}

	public CategoriaDTO(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}
	
	public CategoriaDTO(Categoria categoria) {
		this.id= categoria.getId();
		this.nome = categoria.getNome();
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
	
	
	
	

}
