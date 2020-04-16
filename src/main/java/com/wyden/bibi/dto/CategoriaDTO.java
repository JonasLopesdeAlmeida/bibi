package com.wyden.bibi.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wyden.bibi.model.Categoria;

//Classe DTO criada para trazer somente as categorias sem os livros. 
//Ou seja, traz somente objetos expecíficos para pesquisa.
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	
	private Integer id_categoria;
	//anotacoes do bean validation.
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=3, max=100, message="O tamanho deve ser entre 3 e 100 caracteres")
	private String nome;

	// CONSTRUTOR VAZIO PODE SER IMPORTANTE PARA ALGUMAS BIBLIOTECAS DO JAVA.
	public CategoriaDTO() {

	}
    //Uma sobrecarga do construtor para converter uma lista de categorias...
	//para uma lista de categoriaDTO. 
	//Dessa forma, o construtor recebe um objeto correspondente da minha intidade Categoria..
	//para instanciar o categoriaDTO a partir de uma Categoria.
	public CategoriaDTO(Categoria obj) {
		//atribuindo os valores para a instancia.
		setId_categoria(obj.getId_categoria());
		setNome(obj.getNome());
	}
	public Integer getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(Integer id_categoria) {
		this.id_categoria = id_categoria;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	
	

}
