package com.wyden.bibi.services;

import static org.assertj.core.api.Assertions.assertThat;

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

import com.wyden.bibi.model.Livro;
import com.wyden.bibi.model.enums.StatusLivro;
import com.wyden.bibi.repositories.LivroRepository;

@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class LivroServiceTest {

	@SpyBean
	LivroService service;
	
	@MockBean
	LivroRepository repo;
	
	
	@Test
	public void devePesquisarUmLivroPorID() {
		//cenário
		Integer id_livro = 1;
		Livro livro = cadastrarLivro();
		livro.setId_livro(id_livro);

		Mockito.when(repo.findById(id_livro)).thenReturn(Optional.of(livro));

		//ação
		Livro resultado = service.find(1);

		//verificação
		Assertions.assertThat(resultado).isNotNull();

	}
	
		public static Livro cadastrarLivro() {
			
			return Livro
					.builder()
					.id_livro(1)
					.nome("Senhor dos aneis")
					.autor("desconhecido")
					.editora("jmmd")
					.isbn("1245668")
					.quantidade(1)
					.status(StatusLivro.DISPONIVEL)
					.build();	
		}
	
	
}
