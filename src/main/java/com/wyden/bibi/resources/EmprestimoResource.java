package com.wyden.bibi.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wyden.bibi.model.Emprestimo;
import com.wyden.bibi.services.EmprestimoService;

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoResource {

	@Autowired
	private EmprestimoService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	//metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser qualquer um.
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
         
		Emprestimo obj = service.buscar(id);
		return ResponseEntity.ok().body(obj);
		
		
	}

}
