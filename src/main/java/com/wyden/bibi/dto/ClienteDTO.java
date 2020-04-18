package com.wyden.bibi.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.wyden.bibi.model.Cliente;

public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id_cliente;
	@NotEmpty(message="Preenchimento obrigatorio")
	@Length(min=5, max=100, message="O tamanho deve ser entre 3 e 120 caracteres")
	private String nome;
	@NotEmpty(message="Preenchimento obrigatorio")
	@Email(message="Email invalido")
	private String email;
	
	public ClienteDTO() {
		
	}
	public ClienteDTO(Cliente obj) {
		
		id_cliente = obj.getId_cliente();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}