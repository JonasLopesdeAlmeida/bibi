package com.wyden.bibi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wyden.bibi.model.ItemEmprestimo;

@Repository
public interface ItemEmprestimoRepository extends JpaRepository<ItemEmprestimo, Integer> {

}
