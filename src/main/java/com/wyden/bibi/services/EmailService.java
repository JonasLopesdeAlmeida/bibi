package com.wyden.bibi.services;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.wyden.bibi.model.Emprestimo;

public interface EmailService {

	void sendOrderConfirmationEmail(Emprestimo obj);	
    
	void sendEmail(SimpleMailMessage msg);

	void sendOrderConfirmationHtmlEmail(Emprestimo obj);
	
	void sendHtmlEmail(MimeMessage msg);

}
