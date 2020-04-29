package com.wyden.bibi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.bibi.dto.EnderecoDTO;
import com.wyden.bibi.model.Endereco;
import com.wyden.bibi.repositories.EnderecoRepository;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repo;
	
	

	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Endereco não encontrado! Id: " + id + ", Tipo: " + Endereco.class.getName()));
	}


	public Endereco update(Endereco obj) {
		Endereco newObj = find(obj.getId_endereco());
		// chamando o metodo find caso nao exista o id ele me retorna a excecao.
		updateData(newObj, obj);
		return repo.save(newObj);
	}
	
	public Endereco fromDTO(EnderecoDTO objDTO) {

		return new Endereco(objDTO.getId_endereco(), objDTO.getLogradouro(), objDTO.getNumero(), objDTO.getComplemento(), objDTO.getBairro(), objDTO.getCep(),null);
	}

	// PRIVATE PQ E UM METODO AUXILIAR DA CLASSE.
	private void updateData(Endereco newObj, Endereco obj) {

		newObj.setBairro(obj.getBairro());
		newObj.setCep(obj.getCep());
		newObj.setComplemento(obj.getComplemento());
		newObj.setLogradouro(obj.getLogradouro());
		newObj.setNumero(obj.getNumero());

	}

}
