package com.luciano.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciano.cursomc.domain.Categoria;
import com.luciano.cursomc.repositories.CategoriaRepository;
import com.luciano.cursomc.services.exceptions.ObjectNotFoundExeception;

import javassist.tools.rmi.ObjectNotFoundException;


//anotacao da classe de servico
@Service
public class CategoriaService {
	
	
	//injencao de dependencia
	@Autowired
	private CategoriaRepository repo;
	
	
	//optional dá a opcao de retonar nulo sem erro de nullpointer
	public Categoria buscar(Integer id) {
		Optional<Categoria> categoria = repo.findById(id);
		return  categoria.orElseThrow(() -> new ObjectNotFoundExeception("Objeto não encontrado! Id: " +id+ " , Tipo: " +Categoria.class.getName()));
	}

}
