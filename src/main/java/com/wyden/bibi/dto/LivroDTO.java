package com.wyden.bibi.dto;

import java.io.Serializable;

import com.wyden.bibi.model.Livro;

public class LivroDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id_livro;

	private Integer quantidade;
	private String nome, autor, editora, isbn;

	public LivroDTO() {

	}

	public LivroDTO( Livro obj) {
		id_livro = obj.getId_livro();
		quantidade = obj.getQuantidade();
		nome = obj.getNome();
		editora = obj.getEditora();
		autor = obj.getAutor();
		isbn = obj.getIsbn();

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

	public String getNome() {
		return nome;
	}

	public void setTitulo(String titulo) {
		this.nome = titulo;
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

}
