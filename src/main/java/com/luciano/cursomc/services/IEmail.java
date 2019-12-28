package com.luciano.cursomc.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.luciano.cursomc.domain.Cliente;
import com.luciano.cursomc.domain.Pedido;

public interface IEmail {
	
	public void sendOrderConfirmationEmail(Pedido pedido);
	
	public void sendEmail(SimpleMailMessage msg);
	
	public void sendOrderConfirmationHtmlEmail(Pedido pedido);
	
	public void sendHtmlEmail(MimeMessage msg);

	public void sendNewPasswordEmail(Cliente cliente, String newPassword);

}
