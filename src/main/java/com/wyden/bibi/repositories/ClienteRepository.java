package com.wyden.bibi.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

   // Optional<Cliente>findByMatricula(String matricula);
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	@Query("SELECT obj FROM Cliente obj WHERE matricula LIKE :matricula")
	Page<Cliente> search(@Param("matricula") String matricula, Pageable pageRequest);

}
