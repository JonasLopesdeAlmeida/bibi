package com.wyden.bibi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.bibi.model.Categoria;
import com.wyden.bibi.repositories.CategoriaRepository;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;


@Service
public class CategoriaService {
	@Autowired
    private CategoriaRepository  repo;
	
	public Categoria find(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	
	
	public Categoria insert(Categoria obj ) {
		//o id precisa estar null para que esse metod entenda que e uma insercao e nao uma atualizacao.
		obj.setId_categoria(null);
		return repo.save(obj);
	}
	
	
	public Categoria update(Categoria obj ) {
		//chamando o metodo find caso nao exista o id ele me retorna a excecao.
		find(obj.getId_categoria());
		return repo.save(obj);
	}
	
}
