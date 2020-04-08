package com.wyden.bibi.model.enums;
//atribuindo codigo numerico para Professor e Aluno
public enum TipoCliente {
	PROFESSOR(1, "Professor"),
	ALUNO (2, "Aluno" );
	
	private int cod;
	private String descricao;
	//construtor para atribuir os valores para os tipos enumerados.
	private TipoCliente(int cod, String descricao ) {
		this.cod = cod;
		this.descricao = descricao;			
	}
    //utilizando somente o get pq uma vez instanciado o tipo nao muda.
	public int getCod() {
		return cod;
	}

	public String getDescricao() {
		return descricao;
	}
    
	//metodo que roda um objeto mesmo sem estar instanciado.
	public static TipoCliente toEnum(Integer cod) {
		if(cod == null) {
		
			return null;
			
		}
    
    //percorre todos os valores possíveis do meu status de alerta.
    for(TipoCliente x : TipoCliente.values()) {
			
			if(cod.equals(x.getCod())) {
				
				return x;
			}
			
		}
	throw new IllegalArgumentException("Id invalido: " + cod);
		
	}
}


