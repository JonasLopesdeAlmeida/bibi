package com.wyden.bibi.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.wyden.bibi.security.UserSpringSecurity;

public class UserService {
	
	public static UserSpringSecurity authenticated() {
		//aqui retorna o usuario que estiver logado no sistema.
		try {
		return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			return null;
		}
		}

}
