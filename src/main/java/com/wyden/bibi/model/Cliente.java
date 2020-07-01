package com.wyden.bibi.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wyden.bibi.model.enums.Perfil;
import com.wyden.bibi.model.enums.TipoCliente;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



@Entity
@Builder
@AllArgsConstructor
public class Cliente  implements Serializable  {
	private static final long serialVersionUID = 1L;
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id_cliente;
	private String nome;
	
	@Column(unique=true)
	private String matricula;
	
	@Column(unique=true)
	private String cpf;
	
	@Column(unique=true)
	private String email;
	
	@JsonIgnore //para não aparecer a senha no json
	private String senha;
	
	//internamente o TipoCliente sera armazenado como numero inteiro.
	//porem no fronte ele sera apresentado com um tipo string.
	private Integer tipo;

	//um cliente tem varios enderecos. Uma lista de enderecos.
	//Obs: no endereco ja esta mapeado o cliente. aqui no cliente sera mapeado pelo cliente do lado de enderecos.
	//Obs: Toda alteracao feita em cliente sera refletida em endereco.
	//ex: se eu deletar um cliente eu tb deleto o endereco. Pq foi determinado o CascadeType.ALL.
	//mas se esse cliente ja estiver atrelado em um emprestimo ele nao sera apagado.
	@OneToMany(mappedBy="cliente",  cascade = {CascadeType.ALL})
	private List<Endereco> enderecos = new ArrayList<>();

	//telefone e uma entidade fraca dependente de cliente
	//como ela so tem um atributo, nao sera criado uma classe
	//e sim uma colecao de strings com Set, que nao vai aceitar repeticao.
	@ElementCollection
	//@CollectionTable vai criar uma tabela auxiliar para guardar os telefones.
	@CollectionTable(name="TELEFONE")
	private Set<String> telefones = new HashSet<>();
    
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<>();
	
	@JsonIgnore
	@OneToMany(mappedBy="cliente")
	//O cliente tem uma lista de emprestimos.
	private List<Emprestimo> emprestimos = new ArrayList<>();

	
	public Cliente() {
		//por padrao todos os usuarios adicionados terao o perfil de cliente como padrao.
		addPerfil(Perfil.CLIENTE);
	}
	

   //Obs: colecoes nao entram no construtor: Endereco e Telefone.
	public Cliente(Integer id_cliente, String nome, String matricula, String cpf, String email,String senha, TipoCliente tipo) {
		super();
		this.id_cliente = id_cliente;
		this.nome = nome;
		this.matricula = matricula;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		//para pegar somente o codigo.
		//fazendo uma condicional caso ret
		this.tipo = (tipo==null) ? null : tipo.getCod();
		addPerfil(Perfil.CLIENTE);
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

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	//metodo para converter o numero inteiro para perfil.
	public Set<Perfil> getPerfis(){
		//esta linha e responsavel por retornar o perfis.
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	//metodo para dicionar um perfil
	public void addPerfil(Perfil perfil) {
		
		perfis.add(perfil.getCod());
	}

	public TipoCliente getTipo() {
		return TipoCliente.toEnum(tipo);
	}

	public void setTipo(TipoCliente tipo) {
		this.tipo = tipo.getCod();
	}

	public List<Endereco> getEnderecos() {
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos) {
		this.enderecos = enderecos;
	}

	public Set<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<String> telefones) {
		this.telefones = telefones;
	}
	
	

	public List<Emprestimo> getEmprestimos() {
		return emprestimos;
	}

	public void setEmprestimos(List<Emprestimo> emprestimos) {
		this.emprestimos = emprestimos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id_cliente == null) ? 0 : id_cliente.hashCode());
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
		Cliente other = (Cliente) obj;
		if (id_cliente == null) {
			if (other.id_cliente != null)
				return false;
		} else if (!id_cliente.equals(other.id_cliente))
			return false;
		return true;
	}


}
