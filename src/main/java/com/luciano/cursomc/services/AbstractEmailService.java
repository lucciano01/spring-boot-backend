package com.luciano.cursomc.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;

import com.luciano.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements IEmail {

	@Value("${default.sender}")
	private String sender;
	
	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareMailMessageFromPedido(pedido);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(pedido.getCliente().getEmail());
		mail.setFrom(sender);
		mail.setSubject("Pedido confirmado! Código: " +pedido.getId());
		mail.setSentDate(new Date(System.currentTimeMillis()));
		mail.setText(pedido.toString());
		return mail;
	}
	
}
