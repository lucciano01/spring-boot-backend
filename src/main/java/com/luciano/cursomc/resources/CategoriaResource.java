package com.luciano.cursomc.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.luciano.cursomc.domain.Categoria;
import com.luciano.cursomc.services.CategoriaService;

@RestController
@RequestMapping(value="/categorias")
public class CategoriaResource {
	
	
	//injenção de dependencia
	@Autowired
	private CategoriaService service;
	
	
	//mesmo conceito do REST a interrogação no response é que o retorno pode ser qualquer objeto
	@RequestMapping(method= RequestMethod.GET, value="/{id}")
	public ResponseEntity<?> find(@PathVariable Integer id) {
		Categoria categoria = service.buscar(id);
		return ResponseEntity.ok().body(categoria);
	}
}
