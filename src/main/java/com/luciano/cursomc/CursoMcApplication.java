package com.luciano.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luciano.cursomc.domain.Categoria;
import com.luciano.cursomc.domain.Cidade;
import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.domain.Endereco;
import com.luciano.cursomc.domain.Estado;
import com.luciano.cursomc.domain.Produto;
import com.luciano.cursomc.domain.enums.TipoCliente;
import com.luciano.cursomc.repositories.CategoriaRepository;
import com.luciano.cursomc.repositories.CidadeRepository;
import com.luciano.cursomc.repositories.ClienteRepository;
import com.luciano.cursomc.repositories.EnderecoRepository;
import com.luciano.cursomc.repositories.EstadoRepository;
import com.luciano.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	//a interface CommandLineRunner permite executar alguma ação antes da aplicação iniciar
	
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
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		Produto p1 = new Produto(null, "Computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 800.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		c1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		c2.getProdutos().addAll(Arrays.asList(p2 ));
		
		p1.getCategorias().addAll(Arrays.asList(c1));
		p2.getCategorias().addAll(Arrays.asList(c1,c2));
		p3.getCategorias().addAll(Arrays.asList(c1));
		
		categoriaRepository.saveAll(Arrays.asList(c1,c2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade cid1 = new Cidade(null, "Uberlândia", est1);
		Cidade cid2 = new Cidade(null, "São Paulo", est2);
		Cidade cid3 = new Cidade(null, "Campinas", est2);
		
		est1.getCidades().addAll(Arrays.asList(cid1));
		est2.getCidades().addAll(Arrays.asList(cid2,cid3));
	
		estadoRepository.saveAll(Arrays.asList(est1,est2));
		cidadeRepository.saveAll(Arrays.asList(cid1, cid2,cid3));
		
		Cliente cli1 = new Cliente(null, "Maria", "maria@gmail.com", "36378912377", TipoCliente.PESSOA_FISICA);
		cli1.getTelefones().addAll(Arrays.asList("1236-4567", "7894-5236"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Ap 101", "Cabo", "15.500-000", cli1, cid1);
		Endereco e2 = new Endereco(null, "Rua Eng. Sá" ,"500" , "casa", "Centro", "25.566-800", cli1, cid2);
	
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.save(cli1);
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
			
	
	}

}
