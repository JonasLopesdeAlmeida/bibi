package com.wyden.bibi.resources;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wyden.bibi.dto.EnderecoDTO;
import com.wyden.bibi.model.Endereco;
import com.wyden.bibi.services.EnderecoService;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	private EnderecoService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser
	// qualquer um.
	public ResponseEntity<Endereco> find(@PathVariable Integer id) {

		Endereco obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody EnderecoDTO objDTO, @PathVariable Integer id) {
		Endereco obj = service.fromDTO(objDTO);
		obj.setId_endereco(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}


}
