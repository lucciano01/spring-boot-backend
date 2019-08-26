package com.luciano.cursomc.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.luciano.cursomc.domain.Pedido;
import com.luciano.cursomc.repositories.PedidoRepository;
import com.luciano.cursomc.services.exceptions.ObjectNotFoundExeception;

@Service
public class PedidoService {
	
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public Pedido buscaPedido(Integer id) {
		Optional<Pedido> pedido = pedidoRepository.findById(id);
		return pedido.orElseThrow(()-> new ObjectNotFoundExeception("Pedido não encontrado! Id:" +id+ " Tipo: " + Pedido.class.getName()));
	}

}
