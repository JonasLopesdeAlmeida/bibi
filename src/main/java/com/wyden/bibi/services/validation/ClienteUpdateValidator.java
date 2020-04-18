package com.wyden.bibi.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.wyden.bibi.dto.ClienteDTO;
import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.repositories.ClienteRepository;
import com.wyden.bibi.resources.exceptions.FieldMessage;

public class ClienteUpdateValidator implements ConstraintValidator<ClienteUpdate, ClienteDTO> {
	
	@Autowired
	//responsavel por pegar o id do cliente da URI.
	private HttpServletRequest request;
	
	@Autowired
	private ClienteRepository repo;
	
	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO objDTO, ConstraintValidatorContext context) {
		
		//AQUI VAI PPEGAR O MAP DE URI QUE ESTAO NA REQUISICAO.
		//@SuppressWarnings("unchecked") SOMENTE PARA TIRAR O ALERTA QUE FOI FEITA O CASTING DO MAP.
		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(map.get("id"));
		
		
		List<FieldMessage> list = new ArrayList<>();

		// incluir os testes aqui, inserindo erros na lista
		
		Cliente aux = repo.findByEmail(objDTO.getEmail());
		//aqui verifica quando eu tento atualizar um  email para um cliente e esse email ja pertence a outro clinete existente no banco.
		//nesse caso eu nao posso deixar o email repetir.
		if (aux != null && !aux.getId_cliente().equals(uriId)) {
			list.add(new FieldMessage("email", "Email já existente"));
		}
		
		
		
		
		for (FieldMessage e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}