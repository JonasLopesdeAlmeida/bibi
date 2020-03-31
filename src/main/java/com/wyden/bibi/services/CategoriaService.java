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
	
	public Categoria buscar(Integer id) {
		Optional<Categoria> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Categoria não encontrada! Id: " + id + ", Tipo: " + Categoria.class.getName()));
		}
	
}
