package com.luciano.cursomc.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.luciano.cursomc.domain.Produto;
import com.luciano.cursomc.dto.ProdutoDTO;
import com.luciano.cursomc.resources.utils.URL;
import com.luciano.cursomc.services.ProdutoService;

@RestController
@RequestMapping(value = "/produtos")
public class ProdutoResource {
	
	@Autowired
	private ProdutoService produtoService;
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Produto>find(@PathVariable Integer id){
		Produto produto = produtoService.findById(id);
		return ResponseEntity.ok().body(produto);
	}
	
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(
			@RequestParam(value="nome" , defaultValue="")String nome,
			@RequestParam(value="categorias" , defaultValue="")String categorias,
			@RequestParam(value="page", defaultValue="0")Integer page,
			@RequestParam(value="linesPerPage", defaultValue="20")Integer linesPerPage,
			@RequestParam(value="orderBy" , defaultValue="nome")String orderBy,
			@RequestParam(value="direction", defaultValue="ASC")String direction){
			
			List<Integer> ids = URL.decodeIntTOList(categorias);
			Page<Produto> produtos = produtoService.search(URL.decodeParam(nome), ids, page, linesPerPage, orderBy, direction);
			Page<ProdutoDTO> listDTO = produtos.map(p -> new ProdutoDTO(p));
			
			return ResponseEntity.ok().body(listDTO);
			
		
	}
	

}
