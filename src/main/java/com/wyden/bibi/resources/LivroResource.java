package com.wyden.bibi.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wyden.bibi.dto.LivroDTO;
import com.wyden.bibi.model.Livro;
import com.wyden.bibi.resources.utils.URL;
import com.wyden.bibi.services.LivroService;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	//metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser qualquer um.
	public ResponseEntity<Livro> find(@PathVariable Integer id) {
         
		Livro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
		
	}
	

	//***PAGINACAO***//
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<LivroDTO>> findPage(
			
			@RequestParam(value="nome", defaultValue="") String nome,
			
			@RequestParam(value="categorias", defaultValue="") String categorias,
			
			@RequestParam(value="page", defaultValue="0") Integer page,
			
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
            //OBS: TIVE PROBLEMA COM ATRIBUTOS COMPOSTO USANDO UNDERLINE. EX: NOME_CATEGORIA.
			//QUANDO EXCUTADO A LEITURA ERA FEITA APENAS ATE O NOME.
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		String nomeDecoded = URL.decodeParam(nome);
		List<Integer> ids = URL.decodeIntList(categorias);
		Page<Livro> list = service.search(nomeDecoded, ids, page, linesPerPage,orderBy,direction);	
		Page<LivroDTO> listDTO = list.map(obj -> new LivroDTO(obj));
		return ResponseEntity.ok().body(listDTO);

	}
	
	

}
