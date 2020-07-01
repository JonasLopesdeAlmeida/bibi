package com.wyden.bibi.services;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wyden.bibi.model.Cliente;
import com.wyden.bibi.repositories.ClienteRepository;
import com.wyden.bibi.services.exceptions.ObjectNotFoundException;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ClienteServiceTest {
	
	@SpyBean
	ClienteService service;
	
	@MockBean
	ClienteRepository repo;
	
	@Test
	public void deveAutenticarUmClienteComSucesso() {
		
		//cenario
		String matricula = "125445555";
		String senha = "1234";
		
//		Cliente cliente = Cliente.builder().matricula(matricula).senha(senha).id_cliente(1).build();
//		Mockito.when(repo.findByMatricula(matricula)).thenReturn(Optional.of(cliente));
		
		
	    //acao
		
//		Cliente resultado = service.authenticate(matricula, senha);
		
		
		//verificacao
//		Assertions.assertThat(resultado).isNotNull();
		

	}
	
//	@Test
//	public void deveLancarErroQuandoNaoEncontrarClienteCadastradoComAMatriculaInformada() {
//		
//		//cenario
//		Mockito.when(repo.findByMatricula(Mockito.anyString())).thenReturn(Optional.empty());
//		
//		//acao
//		Throwable exception = Assertions.catchThrowable(() -> service.authenticate("111111111","11111"));
//		
//		
//		//verificacao
//		Assertions.assertThat(exception).isInstanceOf(ObjectNotFoundException.class)
//		.hasMessage("Usuário não encontrado como a matriula informada!");
//		
//	}
//	
	

	

}
