package com.wyden.bibi.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

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

import com.wyden.bibi.dto.ClienteNewDTO;
import com.wyden.bibi.dto.LivroDTO;
import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.model.Livro;
import com.wyden.bibi.resources.utils.URL;
import com.wyden.bibi.services.LivroService;
import com.wyden.bibi.services.exceptions.DataIntegrityException;

@RestController
@RequestMapping(value = "/livros")
public class LivroResource {

	@Autowired
	private LivroService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	//metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser qualquer um.
	public ResponseEntity find(@PathVariable Integer id) {
		
		try {  
		Livro obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		}catch (DataIntegrityException d){
			 return ResponseEntity.badRequest().body(d.getMessage());
		   }
	
	}
	
	
	@RequestMapping(method = RequestMethod.POST)
	// @RequestBody anotcao que faz o Json ser convertido para um objeto
	// automaticamente.
	// @Valid vai fazer a validacao do categoria DTO ANTES de passar a requisicao.
	public ResponseEntity<Void> insert(@Valid @RequestBody LivroDTO objDTO) {
		Livro obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		// pegando a url e passando o numero de resposta.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId_livro())
				.toUri();

		return ResponseEntity.created(uri).build();
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
