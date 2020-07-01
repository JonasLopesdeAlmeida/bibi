package com.wyden.bibi.services;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.repositories.ClienteRepository;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;

@Service
public class AuthService {
	
	@Autowired
	private ClienteRepository clienterepository;
	
	@Autowired
	private BCryptPasswordEncoder pe;
	
	@Autowired
	private EmailService emailService;
	
	private Random rand = new Random();
	
	public void sendNewPassword(String email) {
		
		Cliente cliente = clienterepository.findByEmail(email);
		
		if(cliente == null) {
			throw new ObjectNotFoundException("Email não encontrado.");
		}
		String newPass = newPassword();
		cliente.setSenha(pe.encode(newPass));
		
		clienterepository.save(cliente);
		
		emailService.sendNewPasswordEmail( cliente, newPass);
	}

	private String newPassword() {
		
		char[] vet = new char[10];
		for(int i=0; i<10; i++) {
			vet[i] = randomChar();			
		}

		return new String(vet);
	}

	private char randomChar() {
		int option = rand.nextInt(3);
		if (option == 0) { //gerando um digito
			return (char) (rand.nextInt(10) + 48);
		}
		else if( option == 1) { //gera letra maiuscula
			return (char) (rand.nextInt(26) + 65);
			
		}else { //gera letra minuscula
			
			return (char) (rand.nextInt(26) + 97);
		}
		
	}
	
	

}
