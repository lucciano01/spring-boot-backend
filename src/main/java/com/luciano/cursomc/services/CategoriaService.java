package com.luciano.cursomc.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.luciano.cursomc.domain.Categoria;
import com.luciano.cursomc.repositories.CategoriaRepository;
import com.luciano.cursomc.services.exceptions.DataIntegrityException;
import com.luciano.cursomc.services.exceptions.ObjectNotFoundExeception;

//anotacao da classe de servico
@Service
public class CategoriaService {

	// injencao de dependencia
	@Autowired
	private CategoriaRepository repo;

	// optional dá a opcao de retonar nulo sem erro de nullpointer
	public Categoria find(Integer id) {
		Optional<Categoria> categoria = repo.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundExeception(
				"Objeto não encontrado! Id: " + id + " , Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		return repo.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		find(categoria.getId());
		return repo.save(categoria);
	}

	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		} catch (DataIntegrityViolationException ex) {
			throw new DataIntegrityException("Não é possível excluir uma categaoria que possui produtos");
		}

	}
	
	public List<Categoria> findAll(){
		return repo.findAll();
	}

}
