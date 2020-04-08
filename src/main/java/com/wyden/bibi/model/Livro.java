package com.wyden.bibi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
public class Livro implements Serializable  {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_livro;
	
	private Integer quantidade;
	private String titulo, autor, editora, isbn;
    
	
	//livros tb possi uma lista de categorias.
	@ManyToMany
	//cria uma tabela secular quando se tem associacao muitos para muitos.
	@JoinTable(name= "Livro_Categoria", 
	joinColumns = @JoinColumn(name = "id_livro"), 
	inverseJoinColumns = @JoinColumn(name = "id_categoria")
	)

	@JsonBackReference
	private List<Categoria> categorias = new ArrayList<>();
	
	public Livro() {

	}

	public Livro(Integer id_livro, Integer quantidade, String titulo, String autor, String editora, String isbn) {
		super();
		this.id_livro = id_livro;
		this.quantidade = quantidade;
		this.titulo = titulo;
		this.autor = autor;
		this.editora = editora;
		this.isbn = isbn;
	}

	public Integer getId_livro() {
		return id_livro;
	}

	public void setId_livro(Integer id_livro) {
		this.id_livro = id_livro;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getEditora() {
		return editora;
	}

	public void setEditora(String editora) {
		this.editora = editora;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	
	

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_livro == null) ? 0 : id_livro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Livro other = (Livro) obj;
		if (id_livro == null) {
			if (other.id_livro != null)
				return false;
		} else if (!id_livro.equals(other.id_livro))
			return false;
		return true;
	}

}