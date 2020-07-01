package com.wyden.bibi.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component // dessa forma ela podera ser enjetada em outras classes como componente
public class JWTUtil {

	// pegando a chave que contem o valor no meu aplication.properties e atribuindo
	// a variavel secret.
	@Value("${jwt.secret}")
	private String secret;

	// pegando a chave que contem o valor no meu aplication.properties e atribuindo
	// a variavel expiration.
	@Value("${jwt.expiration}")
	private Long expiration;

	
	//metodo responsavel por gerar um token.
	public String generateToken(String username) {
		//chamando metodo da biblioteca JWT
		return Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration))
	            //definindo o algoritmo para fazer a assinatura do token.
				.signWith(SignatureAlgorithm.HS512, secret.getBytes())
	            .compact();
	}

	//verificando se um token e valido
	public boolean tokenValido(String token) {
		//claims e um tipo do JWT que armazena as revendicacoes do token,
		//o cliente que esta renvidicando esta alegando que ela e a dona do token
		Claims claims = getClaims(token);
		if(claims != null) {
			
			String username = claims.getSubject();
			Date expirationDate = claims.getExpiration();
			Date now = new Date(System.currentTimeMillis());
			if(username != null && expirationDate != null && now.before(expirationDate)) {
				return true;
			}
		}
		return false;
	}
	public String getUsername(String token) {
		Claims claims = getClaims(token);
		if(claims != null) {
			
			return claims.getSubject();
	
		}
		return null;
	}
	
	private Claims getClaims(String token) {
		try {
		  return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody();
		}
		catch (Exception e) {
			return null;
		}
		}

}
