package com.wyden.bibi.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.wyden.bibi.model.Categoria;
import com.wyden.bibi.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

	@org.springframework.transaction.annotation.Transactional(readOnly = true)
	
	@Query("SELECT DISTINCT obj FROM Livro obj INNER JOIN obj.categorias cat WHERE obj.nome LIKE %:nome% AND cat IN :categorias")
	Page<Livro> search(@Param("nome") String nome,@Param("categorias") List<Categoria> categorias, Pageable pageRequest);

	//FAZENO A MESMA REQUISICAO USANDO P SPRINGDATA.
	//SE USAR DESSA FORMA EU NAO PRECISO DA CONSULTA JPQL E O SPRING FAZ A CONSULTA AUTOMATICAMENTE.
	//OBS: SO NAO PODE ESQUECER DE RENOMEAR O METODO search LivroResource para findDistinctByNomeContaininAndCategoriasIn.
	
	//Page<Livro> findDistinctByNomeContaininAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
    
}
