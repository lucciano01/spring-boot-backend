package com.luciano.cursomc.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.luciano.cursomc.domain.Pedido;
import com.luciano.cursomc.services.PedidoService;

@RestController
@RequestMapping(value ="/pedidos")
public class PedidoResource {
	
	@Autowired
	private PedidoService pedidoService;
	
	@RequestMapping(value="/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pedido> buscaPedido(@PathVariable Integer id) {
		Pedido pedido = pedidoService.find(id);
		return ResponseEntity.ok().body(pedido);
	}

}
