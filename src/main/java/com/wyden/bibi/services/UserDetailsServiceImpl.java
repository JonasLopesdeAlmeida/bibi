package com.wyden.bibi.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.repositories.ClienteRepository;
import com.wyden.bibi.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private ClienteRepository repo;

	@Override
	// metodo que recebe um usuario e vai retornar o UserDetail
	public UserDetails loadUserByUsername(String matricula) throws UsernameNotFoundException {
		// implementando uma busca por matricula.
		Optional<Cliente> cli = repo.findByMatricula(matricula);
		if (!cli.isPresent()) {
			throw new UsernameNotFoundException(matricula);
		}

		return new UserSpringSecurity(cli.get().getId_cliente(), cli.get().getMatricula(), cli.get().getSenha(),
				cli.get().getPerfis());
	}

}
