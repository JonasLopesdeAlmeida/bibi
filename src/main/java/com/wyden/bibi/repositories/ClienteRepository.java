package com.wyden.bibi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wyden.bibi.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	Cliente findByCpf(String cpf);
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	Cliente findByMatricula(String matricula);

}
