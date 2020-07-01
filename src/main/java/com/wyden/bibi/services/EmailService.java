package com.wyden.bibi.services;

import org.springframework.mail.SimpleMailMessage;

import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.model.Emprestimo;


public interface EmailService {

	void sendOrderConfirmationEmail(Emprestimo obj);	
    
	void sendEmail(SimpleMailMessage msg);

	void sendNewPasswordEmail(Cliente cliente, String newPass);
//	void sendOrderConfirmationHtmlEmail(Emprestimo obj);
//	
//	void sendHtmlEmail(MimeMessage msg);

}
