package com.wyden.bibi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.wyden.bibi.security.JWTAuthenticationFilter;
import com.wyden.bibi.security.JWTAuthorizationFilter;
import com.wyden.bibi.security.JWTUtil;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	
	@Autowired
	private Environment env;
	
	@Autowired
	private JWTUtil jwtUtil;

	// Definindo um vetor que ira armazenar os caminhos que estaram liberados para o
	// acesso.
	private static final String[] PUBLIC_MATCHERS = { "/h2-console/**" };

	//o cliente da bibi vai poder visualizar as categorias e livros, mas não vai poder alteralos.
	private static final String[] PUBLIC_MATCHERS_GET = { 
			"/livros/**", 
			"/categorias/**" };
	
	private static final String[] PUBLIC_MATCHERS_POST = { 
			"/clientes/**",
			"/emprestimos/**",	
			"/auth/forgot/**" };

	@Override
	// metodo utilizado para acessar e permitir todos os caminhos listados no vetor
	// definido no método acima.
	protected void configure(HttpSecurity http) throws Exception {
		// verificando os prifiles ativos do projeto da bibi ex: test, dev e prod.
		if (Arrays.asList(env.getActiveProfiles()).contains("test")) {
			http.headers().frameOptions().disable();
		}

		http.cors().and().csrf().disable();
		http.authorizeRequests()
		        .antMatchers(HttpMethod.GET, PUBLIC_MATCHERS_GET).permitAll()
		        .antMatchers(HttpMethod.POST, PUBLIC_MATCHERS_POST).permitAll()
		        .antMatchers(PUBLIC_MATCHERS).permitAll()
				// para o os que não estão listados ira ser exigido autenticação.
				.anyRequest().authenticated();
		http.addFilter(new JWTAuthenticationFilter(authenticationManager(), jwtUtil));
		http.addFilter(new JWTAuthorizationFilter(authenticationManager(), jwtUtil, userDetailsService));
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
	}

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		//userDetailsService responsavel por buscar um cliente por matricula.
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}
	
	
	// configurando o cors para liberação de acesso.
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration =  new CorsConfiguration().applyPermitDefaultValues();
		//LIBERANDO O CORS PARA OS METODOS .
		configuration.setAllowedMethods(Arrays.asList("POST","GET","PUT","DELETE","OPTIONS"));
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**",configuration);
		return source;
	}
	
	//bena que eu vou poder enjetar em qualquer classe do meu siste para encriptar senha 
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	

}
