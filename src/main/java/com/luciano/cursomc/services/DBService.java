package com.luciano.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.luciano.cursomc.domain.Categoria;
import com.luciano.cursomc.domain.Cidade;
import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.domain.Endereco;
import com.luciano.cursomc.domain.Estado;
import com.luciano.cursomc.domain.ItemPedido;
import com.luciano.cursomc.domain.Pagamento;
import com.luciano.cursomc.domain.PagamentoComBoleto;
import com.luciano.cursomc.domain.PagamentoComCartao;
import com.luciano.cursomc.domain.Pedido;
import com.luciano.cursomc.domain.Produto;
import com.luciano.cursomc.domain.enums.EstadoPagamento;
import com.luciano.cursomc.domain.enums.TipoCliente;
import com.luciano.cursomc.repositories.CategoriaRepository;
import com.luciano.cursomc.repositories.CidadeRepository;
import com.luciano.cursomc.repositories.ClienteRepository;
import com.luciano.cursomc.repositories.EnderecoRepository;
import com.luciano.cursomc.repositories.EstadoRepository;
import com.luciano.cursomc.repositories.ItemPedidoRepository;
import com.luciano.cursomc.repositories.PagamentoRepository;
import com.luciano.cursomc.repositories.PedidoRepository;
import com.luciano.cursomc.repositories.ProdutoRepository;


@Service
public class DBService {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private PagamentoRepository pagamentoRepository;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private BCryptPasswordEncoder password;
	
	public void instantiateTestDataBase() throws ParseException {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		Categoria c3 = new Categoria(null, "Cama mesa e banho");
		Categoria c4 = new Categoria(null, "Eletrônicos");
		Categoria c5 = new Categoria(null, "Jardinagem");
		Categoria c6 = new Categoria(null, "Decoração");
		Categoria c7 = new Categoria(null, "Perfumaria");
		
		
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		Produto p4 =  new Produto(null, "Mesa", 300.0);
		Produto p5 =  new Produto(null, "Toalha", 50.0);
		Produto p6 =  new Produto(null, "Colcha", 150.0);
		Produto p7 =  new Produto(null, "TV", 2500.0);
		Produto p8 =  new Produto(null, "Roçadeira", 850.0);
		Produto p9 =  new Produto(null, "Abajour", 180.0);
		Produto p10 =  new Produto(null, "Pendente", 120.0);
		Produto p11 = new Produto(null, "Shampoo", 20.0);
		
		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c2.getProdutos().addAll(Arrays.asList(p2,p4 ));
		c3.getProdutos().addAll(Arrays.asList(p5,p6));
		c4.getProdutos().addAll(Arrays.asList(p1,p2,p3,p7));
		c5.getProdutos().addAll(Arrays.asList(p8));
		c6.getProdutos().addAll(Arrays.asList(p9,p10));
		c7.getProdutos().addAll(Arrays.asList(p11));
		
		
		p1.getCategorias().addAll(Arrays.asList(c1,c4));
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().addAll(Arrays.asList(c1,c4));
		p4.getCategorias().addAll(Arrays.asList(c2));
		p5.getCategorias().addAll(Arrays.asList(c3));
		p6.getCategorias().addAll(Arrays.asList(c3));
		p7.getCategorias().addAll(Arrays.asList(c4));
		p8.getCategorias().addAll(Arrays.asList(c5));
		p9.getCategorias().addAll(Arrays.asList(c6));
		p10.getCategorias().addAll(Arrays.asList(c6));
		p11.getCategorias().addAll(Arrays.asList(c7));
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2,c3,c4,c5,c6,c7));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3, p4,p5,p6,p7,p8,p9,p10,p11));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
	
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria", "lucciano01@hotmail.com", "36378912377", TipoCliente.PESSOA_FISICA, password.encode("123"));
		cli1.getTelefones().addAll(Arrays.asList("1236-4567", "7894-5236"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Ap 101", "Cabo", "15.500-000", cli1, cid1);
		Endereco e2 = new Endereco(null, "Rua Eng. Sá" ,"500" , "casa", "Centro", "25.566-800", cli1, cid2);
	
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Pedido ped1 = new Pedido(null, sdf.parse("25/08/2019 15:18"), cli1, e1);
		Pedido ped2 = new Pedido(null, sdf.parse("02/07/2019 08:44"), cli1, e2);
	
		Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);
		Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagto2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
	
		pedidoRepository.saveAll(Arrays.asList(ped1, ped2));
		pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
	
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.0);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.0);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
	
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
	
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
	
	

	}

}
