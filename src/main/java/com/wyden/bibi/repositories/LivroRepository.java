package com.wyden.bibi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wyden.bibi.model.Livro;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Integer> {

}
