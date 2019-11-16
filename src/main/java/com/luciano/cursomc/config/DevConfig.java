package com.luciano.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.luciano.cursomc.services.DBService;
import com.luciano.cursomc.services.IEmail;
import com.luciano.cursomc.services.SmtpEmailService;

@Configuration
@Profile("dev")
public class DevConfig {
	
	//pega o valor dessa propriedade do hibernate do arquivo properties
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Autowired
	private DBService dbService;
	
	@Bean
	public boolean instantiateDataBase() throws ParseException {
	 if(!strategy.equals("create")) {
		return false;
	 }
		 dbService.instantiateTestDataBase();
		return true;
	}
	
	@Bean
	public IEmail emailService() {
		return new SmtpEmailService();
	}

}
