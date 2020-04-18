package com.wyden.bibi.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.wyden.bibi.model.Categoria;
import com.wyden.bibi.model.Livro;
import com.wyden.bibi.repositories.CategoriaRepository;
import com.wyden.bibi.repositories.LivroRepository;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;


@Service
public class LivroService {
	@Autowired
    private LivroRepository  repo;
	
	@Autowired
    private CategoriaRepository  categoriarepository;
	
	public Livro find(Integer id) {
		Optional<Livro> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Livro não encontrado! Id: " + id + ", Tipo: " + Livro.class.getName()));
		}
	
	
	public Page<Livro> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction){
	    //PAGEREQUEST PREPARA AS REQUISICOES PARA FAZER A CONSULTA NO BANCO DE DADOS.
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction),
				orderBy);
		
		List<Categoria> categorias = categoriarepository.findAllById(ids);
		return repo.search(nome, categorias, pageRequest);
		
	}
	
}
