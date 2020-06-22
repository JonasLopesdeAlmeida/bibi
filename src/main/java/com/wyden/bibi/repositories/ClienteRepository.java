package com.wyden.bibi.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wyden.bibi.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	Cliente findByEmail(String email);
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	Cliente findByCpf(String cpf);
	
//	@org.springframework.transaction.annotation.Transactional(readOnly = true)
//	Cliente findByMatricula(String matricula);
	
	boolean existsByMatricula(String matricula);
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)
    Optional<Cliente>findByMatricula(String matricula);
	
//	@org.springframework.transaction.annotation.Transactional(readOnly = true)
//    Optional<Cliente>findBySenha(String senha);
	
	
    
    
    
    
    
    
    
    
    
    
    
	//Obs: REFATORANDO E VERICANDO QUE NÃO EXISTE A NECESSIDADE DE UMA BUSCA JPQL 
	//PARA UMA CONSULTA PAGINADA DE CLIENTE. TENDO EM VISTA QUE UM CLIENTE SÓ POSSUI UMA MATRICULA.
	//OU SEJA, PARA CADA MATRICULA EU SO TENHO O RETORNO DE UM ÚNICO CLIENTE.
	
	//consulta JPQL FAZENDO UMA BUSCA DE UM CLIENTE POR MATRICULA
//	@org.springframework.transaction.annotation.Transactional(readOnly = true)
//	@Query("SELECT obj FROM Cliente obj WHERE matricula LIKE :matricula")
//	Page<Cliente> search(@Param("matricula") String matricula, Pageable pageRequest);

}
