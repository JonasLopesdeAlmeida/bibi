package com.wyden.bibi.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private JWTUtil jwtUtil;
	//como filtro vai precisar analizar o token para ver se ele e valido
	//para isso eu vou ter que extrair o cliente dele e buscar o cliente por matricula
	private UserDetailsService userDetailsService;
	
	public JWTAuthorizationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil, UserDetailsService userDetailsService) {
		super(authenticationManager);
		
		this.jwtUtil = jwtUtil;
		this.userDetailsService = userDetailsService;
	}
	
	//metodo que intercepta a requisicao e verifica se o cliente esta autorizado
    protected void doFilterInternal(HttpServletRequest request,
    		                        HttpServletResponse response,
    		                        FilterChain chain) throws IOException, ServletException{
    	//pegando o valor do cabecalho e armazenando em uma variavel.
    	String header = request.getHeader("Authorization");
        
    	//verificando se o cabecalho e diferente de nulo e se ele comeca com Bearer .
    	if(header != null && header.startsWith("Bearer ") ) {
    		UsernamePasswordAuthenticationToken auth = getAuthentication( request, header.substring(7));
    		//processo de liberacao do cliente que esta tentando acessar o endpoint
    		if(auth != null) {
    			SecurityContextHolder.getContext().setAuthentication(auth);
    		}
    		
    	}
    	//aqui o objeto pode continuar na requisicao.
    	chain.doFilter(request, response);
    	
    }

	private UsernamePasswordAuthenticationToken getAuthentication( HttpServletRequest request ,String token) {
	    
		if(jwtUtil.tokenValido(token)) {
			String username = jwtUtil.getUsername(token);
			UserDetails user = userDetailsService.loadUserByUsername(username);
			
			
		    return new   UsernamePasswordAuthenticationToken(user,user, user.getAuthorities());
		}
		
		return null;
	}
    		                        
	
}
