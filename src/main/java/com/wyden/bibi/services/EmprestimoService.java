package com.wyden.bibi.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wyden.bibi.model.Emprestimo;
import com.wyden.bibi.model.ItemEmprestimo;
import com.wyden.bibi.repositories.EmprestimoRepository;
import com.wyden.bibi.repositories.ItemEmprestimoRepository;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;


@Service
public class EmprestimoService {
	@Autowired
    private EmprestimoRepository  repo;
	
	@Autowired
    private ItemEmprestimoRepository  itememprestimorepository;
//	@Autowired
//    private LivroRepository  livrorepository;
	
	//private LivroService livroService;
	
	
	
	public Emprestimo find(Integer id) {
		Optional<Emprestimo> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Emprestimo não encontrado! Id: " + id + ", Tipo: " + Emprestimo.class.getName()));
		}
	
	@Transactional
	public Emprestimo insert(Emprestimo obj) {
		obj.setId_emprestimo(null);
		obj.setInstante(new Date());
		obj = repo.save(obj);
		for(ItemEmprestimo ip : obj.getItens()) {
			
			ip.setValor(0.0);
			ip.setValor_multa(0.0);
			ip.setEmprestimo(obj);
			//ip.setLivro(obj);
		}
		itememprestimorepository.saveAll(obj.getItens());
	    return obj;
	}
	
}
