package com.wyden.bibi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wyden.bibi.model.enums.StatusLivro;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@AllArgsConstructor
public class Livro implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id_livro;

	private Integer quantidade = 0;
	private String nome, autor, editora, isbn;
	@Enumerated(value = EnumType.STRING)
	private StatusLivro status;

	@JsonIgnore
	// livros tb possui uma lista de categorias.
	@ManyToMany
	// cria uma tabela secular quando se tem associacao muitos para muitos.
	@JoinTable(name = "Livro_Categoria", joinColumns = @JoinColumn(name = "id_livro"), inverseJoinColumns = @JoinColumn(name = "id_categoria"))

	
	private List<Categoria> categorias = new ArrayList<>();

	@JsonIgnore
	// Mais uma vez O set vai garantir que eu tenha item repetido no emprestimo.
	@OneToMany(mappedBy="id.livro")
	private Set<ItemEmprestimo> itens = new HashSet<>();

	public Livro() {

	}

	public Livro(Integer id_livro, Integer quantidade, String nome, String autor, String editora, String isbn, StatusLivro status) {
		super();
		this.id_livro = id_livro;
		this.quantidade = quantidade;
		this.nome = nome;
		this.autor = autor;
		this.editora = editora;
		this.isbn = isbn;
		this.status = status;
	}
	

	//nao serializar essa lista tb.
	@JsonIgnore
	//O livro conhece os EMPRESTIMOS. Dessa forma foi criado um getEmprestimos
	//para varendo os itens de emprestimo e criando uma lista emprestimos associados a esses itens.
	public List<Emprestimo> getEmprestimos() {

		List<Emprestimo> lista = new ArrayList<>();
        //percorrendo a lista de itens que ja existem na classe.
		for (ItemEmprestimo x : itens) {
            //para cada item de emprestimo existente eu adiciono na lista.
			lista.add(x.getEmprestimo());
		}

		return lista;
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

	public void setNome(String nome) {
		this.nome = nome;
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

	public Set<ItemEmprestimo> getItens() {
		return itens;
	}

	public void setItens(Set<ItemEmprestimo> itens) {
		this.itens = itens;
	}

	
	public StatusLivro getStatus() {
		return status;
	}

	public void setStatus(StatusLivro status) {
		this.status = status;
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
