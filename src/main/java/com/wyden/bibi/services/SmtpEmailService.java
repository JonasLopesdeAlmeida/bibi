package com.wyden.bibi.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;


public class SmtpEmailService extends AbstractEmailService {

	@Autowired
	private MailSender mailSender;
	
//	@Autowired
//	private JavaMailSender javaMailsender;
	
	
	private static final Logger LOG = LoggerFactory.getLogger(SmtpEmailService.class);
	
	@Override
	public void sendEmail(SimpleMailMessage msg) {
		LOG.info("Enviando de email...");
		mailSender.send(msg);
		LOG.info("Email enviado");
		
	}

//	@Override
//	public void sendHtmlEmail(MimeMessage msg) {
//		LOG.info("Enviando de email...");
//		javaMailsender.send(msg);
//		LOG.info("Email enviado");
//		
//	}

	
	
}
