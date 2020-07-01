package com.wyden.bibi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.model.Emprestimo;

@Repository
public interface EmprestimoRepository extends JpaRepository<Emprestimo, Integer> {

	//fazendo a busca de um emprestimo por cliente
	Page<Emprestimo> findByCliente(Cliente cliente, Pageable pageRequest );
	
}
