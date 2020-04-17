package com.wyden.bibi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wyden.bibi.dto.ClienteDTO;
import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.repositories.ClienteRepository;
import com.wyden.bibi.services.exceptions.DataIntegrityException;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	@Autowired
    private ClienteRepository  repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
	public Cliente update(Cliente obj ) {
		Cliente newObj = find(obj.getId_cliente());
		//chamando o metodo find caso nao exista o id ele me retorna a excecao.
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public void delete(Integer id) {
		find(id);
		try {
			repo.deleteById(id);
		}
		catch(DataIntegrityViolationException e) {
			//Tratamento de exceção personalizado para alertar a falha ao excluir uma categoria deu possui livros atrelas a ela..
			throw new DataIntegrityException("Não é possivel excluir porque ha entidades relacionadas.");
			
		}
		
	}
	
	public List<Cliente> findAll(){
		return repo.findAll();
		
	}
	
	//**PAGINACAO**//
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
	    //PAGEREQUEST PREPARA AS REQUISICOES PARA FAZER A CONSULTA NO BANCO DE DADOS.
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		//AQUI RETONA A PAGINA A PARTIR DO pageRequest.
		return repo.findAll(pageRequest);
		
	}
	
	//retorno ainda falta fazer.
	public Cliente fromDTO(ClienteDTO objDTO) {
		
		return new Cliente(objDTO.getId_cliente(), objDTO.getNome(), null, null, objDTO.getEmail(), null);
	}
	
	//PRIVATE PQ E UM METODO AUXILIAR DA CLASSE.
	private void updateData(Cliente newObj, Cliente obj) {
		
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());
	
	}
	
}
