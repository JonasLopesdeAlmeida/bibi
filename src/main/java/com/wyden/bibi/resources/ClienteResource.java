package com.wyden.bibi.resources;

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

import com.wyden.bibi.dto.ClienteDTO;
import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.services.ClienteService;

@RestController
@RequestMapping(value = "/clientes")
public class ClienteResource {

	@Autowired
	private ClienteService service;
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
	//metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser qualquer um.
	public ResponseEntity<Cliente> find(@PathVariable Integer id) {
         
		Cliente obj = service.find(id);
		return ResponseEntity.ok().body(obj);
		
		
	}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
	Cliente obj = service.fromDTO(objDTO);
	obj.setId_cliente(id);
    obj = service.update(obj);
    return ResponseEntity.noContent().build();
}
	
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	// metodo que encapsula todas as erequisicoes HTTP. ? para dizer que pode ser
	// qualquer um.
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		service.delete(id);
		return ResponseEntity.noContent().build();

	}
	
	//metodo para retornar somente as categorias.
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {
		List<Cliente> list = service.findAll();
		//linha de codigo responsavel para converter uma lista para outra lista usando o "map" com uma arrow function. ->
		List<ClienteDTO> listDTO = list.stream().map(obj -> new ClienteDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);

	}
	
	//***PAGINACAO***//
	@RequestMapping(value="/page", method = RequestMethod.GET)
	public ResponseEntity<Page<ClienteDTO>> findPage(
			
			@RequestParam(value="page", defaultValue="0") Integer page,
			
			@RequestParam(value="linesPerPage", defaultValue="24")Integer linesPerPage, 
            //OBS: TIVE PROBLEMA COM ATRIBUTOS COMPOSTO USANDO UNDERLINE. EX: NOME_CATEGORIA.
			//QUANDO EXCUTADO A LEITURA ERA FEITA APENAS ATE O NOME.
			@RequestParam(value="orderBy", defaultValue="nome")String orderBy, 
			
			@RequestParam(value="direction", defaultValue="ASC")String direction) {
		
		Page<Cliente> list = service.findPage(page, linesPerPage,orderBy,direction);
		
		Page<ClienteDTO> listDTO = list.map(obj -> new ClienteDTO(obj));
		return ResponseEntity.ok().body(listDTO);

	}
	

}
