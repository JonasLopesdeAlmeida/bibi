package com.wyden.bibi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.bibi.model.Emprestimo;
import com.wyden.bibi.repositories.EmprestimoRepository;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;


@Service
public class EmprestimoService {
	@Autowired
    private EmprestimoRepository  repo;
	
	public Emprestimo find(Integer id) {
		Optional<Emprestimo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Emprestimo não encontrado! Id: " + id + ", Tipo: " + Emprestimo.class.getName()));
		}
	
}
