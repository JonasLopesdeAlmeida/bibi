package com.wyden.bibi.repositories;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wyden.bibi.model.Cliente;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class ClienteRepositoryTest {
     
	@Autowired
	ClienteRepository repo;
	
	@Test
	public void deveVerificarAexistenciaDeUmaMatricula() {
		
		//cenario
		//criar cliente
		Cliente cliente = criarCliente();
		repo.save(cliente);

		//acao
		//validar aexitencia de uma matricula do cliente que foi cadastrado
		boolean resultado = repo.existsByMatricula("154224455");

		//verificacao
		Assertions.assertThat(resultado).isTrue();
	
	}
	
	@Test
	public void deveRetornarFalsoQuandoNaoHouverClienteCadastradoComMatricula() {
		
		//cenario
		repo.deleteAll();
		
		//acao
		boolean resultado = repo.existsByMatricula("154224455");
		
		//verificacao
		Assertions.assertThat(resultado).isFalse();
		
		
	}

	public static Cliente criarCliente() {
		
		return Cliente
				.builder()
				.id_cliente(null)
				.nome("jonas")
				.matricula("154224455")
				.cpf("11111111")
				.email("email@email.com")
				.build();
		
	}
	
	
	

}
