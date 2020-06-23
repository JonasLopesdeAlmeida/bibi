package com.wyden.bibi.config;

import java.text.ParseException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.wyden.bibi.services.EmailService;
import com.wyden.bibi.services.SmtpEmailService;
 
@Configuration
@Profile("prod")
public class ProdConfig {
 
 @Bean
 public boolean instantiateDataBase() throws ParseException {
 return true;
 }
 
 @Bean
 public EmailService emailService() {
 return new SmtpEmailService();
 }
}