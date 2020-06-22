package com.wyden.bibi.services;

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

import com.wyden.bibi.model.Emprestimo;

public abstract class AbstractEmailService implements EmailService {
    @Value("${default.sender}")
	private String sender; 
    
    @Autowired
    private TemplateEngine templateEngine;
	
    @Autowired
    private JavaMailSender javaMailSender;
    
    @Override
	public void sendOrderConfirmationEmail(Emprestimo obj) {
		SimpleMailMessage sm = prepareSimpleMailMessageFromEmprestimo(obj);
	    sendEmail(sm);
	}

	protected SimpleMailMessage prepareSimpleMailMessageFromEmprestimo(Emprestimo obj) {
		SimpleMailMessage sm = new SimpleMailMessage();
		sm.setTo(obj.getCliente().getEmail());
		sm.setFrom(sender);
		sm.setSubject("Emprestimo confirmado! Código: " + obj.getId_emprestimo());
		sm.setSentDate(new Date(System.currentTimeMillis()));
		sm.setText(obj.toString());
		return sm;
	}
 
	protected String htmlFromTemplateEmprestimo(Emprestimo obj) {
		
		Context context = new Context();
	    context.setVariable("emprestimo", obj);
	    return templateEngine.process("email/confirmacaoEmprestimo", context);
	      
	}
	
	@Override
	public void sendOrderConfirmationHtmlEmail(Emprestimo obj) {
		try {
		MimeMessage mm = prepareMimeMessageFromEmprestimo(obj);
	    sendHtmlEmail(mm);
		}
		catch (MessagingException e) {
			sendOrderConfirmationHtmlEmail(obj);
		}
	    }

	protected MimeMessage prepareMimeMessageFromEmprestimo(Emprestimo obj) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		MimeMessageHelper mmh = new MimeMessageHelper(mimeMessage, true);
		mmh.setTo(obj.getCliente().getEmail());
		mmh.setFrom(sender);
		mmh.setSubject("Emprestimo confirmado! Código: " + obj.getId_emprestimo());
		mmh.setSentDate(new Date(System.currentTimeMillis()));
	    mmh.setText(htmlFromTemplateEmprestimo(obj), true);
	
	    return mimeMessage;
	}
	
}