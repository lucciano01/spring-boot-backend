package com.luciano.cursomc.services;

import java.util.Date;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.luciano.cursomc.domain.Pedido;

public abstract class AbstractEmailService implements IEmail {

	@Value("${default.sender}")
	private String sender;

	@Autowired
	private TemplateEngine template;

	@Autowired
	private JavaMailSender javaMail;

	@Override
	public void sendOrderConfirmationEmail(Pedido pedido) {
		SimpleMailMessage sm = prepareMailMessageFromPedido(pedido);
		sendEmail(sm);
	}

	protected SimpleMailMessage prepareMailMessageFromPedido(Pedido pedido) {
		SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(pedido.getCliente().getEmail());
		mail.setFrom(sender);
		mail.setSubject("Pedido confirmado! Código: " + pedido.getId());
		mail.setSentDate(new Date(System.currentTimeMillis()));
		mail.setText(pedido.toString());
		return mail;
	}

	protected String htmlFromTemplatePedido(Pedido pedido) {
		Context context = new Context();
		context.setVariable("pedido", pedido);
		return template.process("email/confirmacaoPedido", context);

	}

	public void sendOrderConfirmationHtmlEmail(Pedido pedido) {
		try {
			
			MimeMessage mm = prepareMimeMessageFromPedido(pedido);
			sendHtmlEmail(mm);

		} catch (MessagingException ex) {
			sendOrderConfirmationEmail(pedido);
		}
	}

	protected MimeMessage prepareMimeMessageFromPedido(Pedido pedido) throws MessagingException {
		MimeMessage mm = javaMail.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mm, true);
		mmh.setTo(pedido.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Pedido confirmado! Código: " + pedido.getId());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
		mmh.setText(htmlFromTemplatePedido(pedido), true);
		return mm;
	}

}
