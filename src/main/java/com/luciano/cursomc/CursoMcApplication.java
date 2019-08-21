package com.luciano.cursomc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.luciano.cursomc.domain.Categoria;
import com.luciano.cursomc.repositories.CategoriaRepository;

@SpringBootApplication
public class CursoMcApplication implements CommandLineRunner {

	//a interface CommandLineRunner permite executar alguma ação antes da aplicação iniciar
	
	@Autowired
	private CategoriaRepository repo;
	
	public static void main(String[] args) {
		SpringApplication.run(CursoMcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria c1 = new Categoria(null, "Informática");
		Categoria c2 = new Categoria(null, "Escritório");
		repo.saveAll(Arrays.asList(c1,c2));
	}

}
