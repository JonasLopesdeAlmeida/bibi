package com.wyden.bibi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.wyden.bibi.dto.CategoriaDTO;
import com.wyden.bibi.model.Categoria;
import com.wyden.bibi.services.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaResource {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	// metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser
	// qualquer um.
	public ResponseEntity<Categoria> find(@PathVariable Integer id) {

		Categoria obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}
 
	@RequestMapping(method = RequestMethod.POST)
	//@RequestBody anotcao que faz o Json ser convertido para um objeto automaticamente.
	//@Valid vai fazer a validacao do categoria DTO ANTES de passar a requisicao.
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoriaDTO objDto) {
		Categoria obj = service.fromDTO(objDto);
		//esqueci de colocar essa linha de codigo e por isso nao estava inserindo.
		obj = service.insert(obj);
		// pegando a url e passando o numero de resposta.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getId_categoria()).toUri();
		
	   return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody CategoriaDTO objDTO, @PathVariable Integer id_categoria) {
	Categoria obj = service.fromDTO(objDTO);
	obj.setId_categoria(id_categoria);
    obj = service.update(obj);
    return ResponseEntity.noContent().build();
}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser
	// qualquer um.
	public ResponseEntity<Void> delete(@PathVariable Integer id_categoria) {

		service.delete(id_categoria);
		return ResponseEntity.noContent().build();

	}
	
	//metodo para retornar somente as categorias.
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {
		List<Categoria> list = service.findAll();
		//linha de codigo responsavel para converter uma lista para outra lista usando o "map" com uma arrow function. ->
		List<CategoriaDTO> listDto = list.stream().map(obj -> new CategoriaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);

	}
	
	//***PAGINACAO***//
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<CategoriaDTO>> findPage(
			
			@RequestParam(value="page", defaultValue="0") Integer page,
			
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
            //OBS: TIVE PROBLEMA COM ATRIBUTOS COMPOSTO USANDO UNDERLINE. EX: NOME_CATEGORIA.
			//QUANDO EXCUTADO A LEITURA ERA FEITA APENAS ATE O NOME.
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		Page<Categoria> list = service.findPage(page, linesPerPage,orderBy,direction);
		
		Page<CategoriaDTO> listDTO = list.map(obj -> new CategoriaDTO(obj));
		return ResponseEntity.ok().body(listDTO);

	}
	
		
	
	
	}
