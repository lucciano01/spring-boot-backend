package com.luciano.cursomc.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luciano.cursomc.domain.Categoria;
import com.luciano.cursomc.domain.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	
	 //consulta comum
	 @Transactional(readOnly=true)
	 @Query("select distinct p from Produto p inner join p.categorias cat where p.nome like %:nome% and cat in :categorias")
	 Page<Produto>search(@Param("nome")String nome, @Param("categorias")List<Categoria> categorias, Pageable pageRequest);

	 //consulta utilizando padrão de nomes (ver documentação do Spring Data)
	 //Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);

}
