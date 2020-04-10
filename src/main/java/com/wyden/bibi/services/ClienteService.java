package com.wyden.bibi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.repositories.ClienteRepository;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;


@Service
public class ClienteService {
	@Autowired
    private ClienteRepository  repo;
	
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
		"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
		}
	
}
