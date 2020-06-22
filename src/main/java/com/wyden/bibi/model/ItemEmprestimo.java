package com.wyden.bibi.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemEmprestimo implements Serializable  {
	private static final long serialVersionUID = 1L;
	// Obs: uma classe de associacao nao tem ID proprio.
	// quem vai identifica ela sao as duas classes relacionadas
	// a ela: Livro e Emprestimo.
	// Dessa forma tera uma chave composta contendo o Livro e
	// Emprestimo.
	
	//nao vai ser serializado.
	@JsonIgnore
	@EmbeddedId
	//anotacao usada para dizer que esse id esta embutido em uma classe auxiliar.
	private ItemEmprestimoPK id = new ItemEmprestimoPK();
	
	private Double valor_multa ;
	private Integer quantidade;
	private Double valor = 2.0;
	
	public ItemEmprestimo() {
		
		
	}

	public ItemEmprestimo(Livro livro, Emprestimo emprestimo, Double valor_multa, Integer quantidade, Double valor) {
		super();
		//atribuindo o Livro e o Emprestimo dentro do objeto ID.
		id.setEmprestimo(emprestimo);
		id.setLivro(livro);
		this.valor_multa = valor_multa;
		this.quantidade = quantidade;
		this.valor = valor;
	}
	//aqui o valor do metodo sera reconhecido pelo json e serializado.
	//Obs: esse metodo sera implementado apos confirmar como faz o calculo da multa na biblioteca wyden.
//	public double getSubTotal() {
//		
//		return valor = valor_multa;
//		
//	}
	
	@JsonIgnore
	//atribuindo os get`s para Emprestimo e Livro para ter acesso a eles fora da classe ItemEmprestimo.
	public Emprestimo getEmprestimo() {
		
		return id.getEmprestimo();
	}
	
	
	public void setEmprestimo(Emprestimo emprestimo) {
		id.setEmprestimo(emprestimo);
	}

	public Livro getLivro() {
		
		return id.getLivro();
	}
	
	public void setLivro(Livro livro) {
		id.setLivro(livro);
	}


	public ItemEmprestimoPK getId() {
		return id;
	}

	public void setId(ItemEmprestimoPK id) {
		this.id = id;
	}

	public Double getValor_multa() {
		return valor_multa;
	}

	public void setValor_multa(Double valor_multa) {
		this.valor_multa = valor_multa;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		ItemEmprestimo other = (ItemEmprestimo) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(getLivro().getNome());
		builder.append(", Qtd: ");
		builder.append(getQuantidade());
		builder.append("\n");
		return builder.toString();
	}
	
	
	

}
