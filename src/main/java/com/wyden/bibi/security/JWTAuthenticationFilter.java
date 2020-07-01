package com.wyden.bibi.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wyden.bibi.dto.CredenciaisDTO;

//nesta classe vai dizer para o spring que ele vai ter que interceptar a requisicao de um login.
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private AuthenticationManager authenticationManager;

	private JWTUtil jwtUtil;

	//fazendo a injecao das classe acima diretamente pelo o construtor.
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}

	@Override
	// metodo de tentativa de autenticacao do spring.
	public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res)
			throws AuthenticationException {

		try {
			//metodo que vai tentar pegar um objeto passado na requisicao.
			//obs: esse metodo e do spring
			CredenciaisDTO creds = new ObjectMapper().readValue(req.getInputStream(), CredenciaisDTO.class);

			//apos ele vai instanciar o que foi passado com matricula e senha
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
					creds.getMatricula(), creds.getSenha(), new ArrayList<>());
            //ao final havera uma verificacao se matricula e senha passadas sao validas.
			//ele faz isso com base no que foi implementado no UserDetails e UserDetailsService
			Authentication auth = authenticationManager.authenticate(authToken);
			//se estiver correto ele deixa passar e se estiver errado ele retorna um erro de autenticacao.
			return auth;
		
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	//metodo que diz o que deve fazer se a autenticacao for feita com sucesso.
	protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain,
			Authentication auth) throws IOException, ServletException {

		String username = ((UserSpringSecurity) auth.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		//token passado como cabecalho da resposta
		res.addHeader("Authorization", "Bearer " + token);
		//autorizando o cors
		res.addHeader("access-control-expose-headers", "Authorization");
	}

	private class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
				AuthenticationException exception) throws IOException, ServletException {
			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().append(json());
		}

		private String json() {
			long date = new Date().getTime();
			return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Não autorizado\", "
					+ "\"message\": \"Matricula ou senha inválidos\", " + "\"path\": \"/login\"}";
		}
	}
}