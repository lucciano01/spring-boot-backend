package com.luciano.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.luciano.cursomc.domain.Pedido;

public interface IEmail {
	
	public void sendOrderConfirmationEmail(Pedido pedido);
	
	public void sendEmail(SimpleMailMessage msg);

}
