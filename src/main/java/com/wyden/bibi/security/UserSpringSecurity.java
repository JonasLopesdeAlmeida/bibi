package com.wyden.bibi.security;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.wyden.bibi.model.enums.Perfil;

public class UserSpringSecurity implements  UserDetails{

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String matricula;
	private String senha;
	private Collection<? extends GrantedAuthority> authorities;
	
	public UserSpringSecurity() {
		
	}
	
	
	
	
	public UserSpringSecurity(Integer id, String matricula, String senha,
			Set<Perfil> perfis) {
		super();
		this.id = id;
		this.matricula = matricula;
		this.senha = senha;
		this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
	
	}




	//recuperando um cliente por id.
	public Integer getId() {
		return id;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return senha;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return matricula;
	}

	@Override
	//metodo que pode ser implementado para expirar conta de usuario.
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
    //metodo que verifica o perfil do usuario.
	public boolean hasRole(Perfil perfil) {
		return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
		
	}
	
}
