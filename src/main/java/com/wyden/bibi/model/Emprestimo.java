package com.wyden.bibi.model;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;

@Entity
@Builder
@AllArgsConstructor
public class Emprestimo implements Serializable  {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_emprestimo;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date instante;
	
	@JsonFormat(pattern="dd/MM/yyyy HH:mm")
	private Date datadeEntrega;
		
	@ManyToOne
	@JoinColumn(name="cliente_id")
	private Cliente cliente;
	
	private double valor_multa;
    
	@ManyToOne
	@JoinColumn(name="endereco_do_cliente_id")
	private Endereco enderecoDoCliente;

	//Mais uma vez O set vai garantir que eu tenha item repetido no emprestimo.
	@OneToMany(mappedBy="id.emprestimo")
	private Set<ItemEmprestimo> itens = new HashSet<>();
	
    public Emprestimo() {
    	
    	
    }

	public Emprestimo(Integer id_emprestimo, Date instante, Cliente cliente, Endereco enderecoDoCliente, Date datadeEntrega) {
		super();
		this.id_emprestimo = id_emprestimo;
		this.instante = instante;
		this.cliente = cliente;
		this.enderecoDoCliente = enderecoDoCliente;
		this.datadeEntrega = datadeEntrega;
		//this.setValor_multa(valor_multa);
	}

	public Integer getId_emprestimo() {
		return id_emprestimo;
	}

	public void setId_emprestimo(Integer id_emprestimo) {
		this.id_emprestimo = id_emprestimo;
	}

	public Date getInstante() {
		return instante;
	}

	public void setInstante(Date instante) {
		this.instante = instante;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Endereco getEnderecoDoCliente() {
		return enderecoDoCliente;
	}

	public void setEnderecoDoCliente(Endereco enderecoDoCliente) {
		this.enderecoDoCliente = enderecoDoCliente;
	}

	public Set<ItemEmprestimo> getItens() {
		return itens;
	}

	public void setItens(Set<ItemEmprestimo> itens) {
		this.itens = itens;
	}

	public Date getDatadeEntrega() {
		return datadeEntrega;
	}

	public void setDatadeEntrega(Date datadeEntrega) {
		this.datadeEntrega = datadeEntrega;
	}
	
	public double getValor_multa() {
		return valor_multa;
	}

	public void setValor_multa(double valor_multa) {
		this.valor_multa = valor_multa;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_emprestimo == null) ? 0 : id_emprestimo.hashCode());
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
		Emprestimo other = (Emprestimo) obj;
		if (id_emprestimo == null) {
			if (other.id_emprestimo != null)
				return false;
		} else if (!id_emprestimo.equals(other.id_emprestimo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm" );
		builder.append("Emprestimo número: ");
		builder.append(getId_emprestimo());
		builder.append(", Data do emprestimo: ");
		builder.append(sdf.format(getInstante()));
		builder.append(", Data de devolução: ");
		builder.append(sdf.format(getDatadeEntrega()));
		builder.append(", Cliente: ");
		builder.append(getCliente().getNome());
		builder.append("\nDetalhes: \n");
		for(ItemEmprestimo ip : getItens()) {
			builder.append(ip.toString());
		}	
		return builder.toString();
	}

	
	

    

}
