package com.wyden.bibi.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.wyden.bibi.dto.ClienteNewDTO;
import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.repositories.ClienteRepository;
import com.wyden.bibi.resources.exceptions.FieldMessage;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteInsert ann) {
	}

	@Override
	public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
		List<FieldMessage> list = new ArrayList<>();

		// incluir os testes aqui, inserindo erros na lista
		
		Cliente aux = repo.findByEmail(objDTO.getEmail());
		if (aux != null) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		Cliente aux2 = repo.findByCpf(objDTO.getCpf());
		if (aux2 != null) {
			list.add(new FieldMessage("Cpf", "Cpf já existente"));
		}
		
		Cliente aux3 = repo.findByMatricula(objDTO.getMatricula());
		if (aux3 != null) {
			list.add(new FieldMessage("Matricula", "Matricula já existente"));
		}
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}