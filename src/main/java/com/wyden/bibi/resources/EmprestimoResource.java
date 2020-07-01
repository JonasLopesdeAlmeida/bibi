package com.wyden.bibi.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wyden.bibi.model.Emprestimo;
import com.wyden.bibi.services.EmprestimoService;
import com.wyden.bibi.services.LivroService;

@RestController
@RequestMapping(value = "/emprestimos")
public class EmprestimoResource {

	@Autowired
	private EmprestimoService service;
	
	@Autowired
	private LivroService livroService;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	//metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser qualquer um.
	public ResponseEntity<Emprestimo> find(@PathVariable Integer id) {
         
		Emprestimo obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
		
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Emprestimo obj) {
		//esqueci de colocar essa linha de codigo e por isso nao estava inserindo.
		obj = service.insert(obj);
		
		//obj2 = livroService.updatebookquantity(obj2);
		
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId_emprestimo()).toUri();
		
	   return ResponseEntity.created(uri).build();
	}
	
	//***PAGINACAO***//
		@RequestMapping( method = RequestMethod.GET)
		public ResponseEntity<Page<Emprestimo>> findPage(
				
				@RequestParam(value="page", defaultValue="0") Integer page,
				
				@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
	            //OBS: TIVE PROBLEMA COM ATRIBUTOS COMPOSTO USANDO UNDERLINE. EX: NOME_CATEGORIA.
				//QUANDO EXCUTADO A LEITURA ERA FEITA APENAS ATE O NOME.
				@RequestParam(value="orderBy", defaultValue="instante")String orderBy, 
				
				@RequestParam(value="direction", defaultValue="DESC")String direction) {
			
			Page<Emprestimo> list = service.findPage(page, linesPerPage,orderBy,direction);
			
			return ResponseEntity.ok().body(list);

		}

}
