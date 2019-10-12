package com.luciano.cursomc;

import java.text.SimpleDateFormat;
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

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	//a interface CommandLineRunner permite executar alguma ação antes da aplicação iniciar
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
	}

}
