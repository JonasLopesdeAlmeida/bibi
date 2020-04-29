package com.wyden.bibi.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.wyden.bibi.dto.ClienteDTO;
import com.wyden.bibi.dto.ClienteNewDTO;
import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.services.ClienteService;
import com.wyden.bibi.services.exceptions.DataIntegrityException;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	// metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser
	// qualquer um.
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {

		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);

	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	// @RequestBody anotcao que faz o Json ser convertido para um objeto
	// automaticamente.
	// @Valid vai fazer a validacao do categoria DTO ANTES de passar a requisicao.
	public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO objDTO) {
		Cliente obj = service.fromDTO(objDTO);
		obj = service.insert(obj);
		// pegando a url e passando o numero de resposta.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId_cliente())
				.toUri();

		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
		Cliente obj = service.fromDTO(objDTO);
		obj.setId_cliente(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = "application/json")
	// metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser
	// qualquer um.
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		service.delete(id);
		return ResponseEntity.noContent().build();

	}

	 //metodo para retornar todos os clientes.
	 @RequestMapping(method = RequestMethod.GET)
	 public ResponseEntity<List<ClienteDTO>> findAll() {
	 List<Cliente> list = service.findAll();
	// linha de codigo responsavel para converter uma lista para outra lista usando
	// o "map" com uma arrow function. ->
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}

	// ***PAGINACAO***//
	@RequestMapping(value = "/page", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Page<ClienteDTO>> findPage(

			@RequestParam(value = "page", defaultValue = "0") Integer page,

			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			// OBS: TIVE PROBLEMA COM ATRIBUTOS COMPOSTO USANDO UNDERLINE. EX:
			// NOME_CATEGORIA.
			// QUANDO EXCUTADO A LEITURA ERA FEITA APENAS ATE O NOME.
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,

			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);

		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);

	}

	//TESTANDO A ANOTAÇÃO: @PostMapping VERSÃO REDUZIDA.
	@PostMapping("/autenticar")
	public ResponseEntity authenticate(@RequestBody ClienteDTO obj) {		
		   try {
			   Cliente clienteAutenticado =  service.authenticate(obj.getMatricula());
			     //aqui retornando o codigo 200 e o corpo da requisicao.  
				 return ResponseEntity.ok(clienteAutenticado);
			   }catch (DataIntegrityException d){
				 return ResponseEntity.badRequest().body(d.getMessage());
			   }

	}
	
	
	
	
	

}
