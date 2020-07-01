package com.wyden.bibi.model.enums;
//atribuindo codigo numerico para Professor e Aluno
public enum Perfil {
	//A string "ROLE_" é próprio do spring security.
	ADMIN(1, "ROLE_ADMIN"),
	CLIENTE (2, "ROLE_CLIENTE" );
	
	private int cod;
	private String descricao;
	//construtor para atribuir os valores para os tipos enumerados.
	private Perfil(int cod, String descricao ) {
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
	public static Perfil toEnum(Integer cod) {
		if(cod == null) {
		
			return null;
			
		}
    
    //percorre todos os valores possíveis do meu status de alerta.
    for(Perfil x : Perfil.values()) {
			
			if(cod.equals(x.getCod())) {
				
				return x;
			}
			
		}
	throw new IllegalArgumentException("Id invalido: " + cod);
		
	}
}


