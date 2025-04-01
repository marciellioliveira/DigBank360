package br.com.marcielli.DigBank360.helpers;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

public enum TipoDeCartao {
	
	CREDITO("CREDITO"),
	DEBITO("DEBITO");
		
	private final String descricao;

//	@JsonValue
	public String getDescricao() {
		return descricao;
	}	
	
	private TipoDeCartao(String descricao) {
		this.descricao = descricao;
	}	
	
//	@JsonCreator
//	public static TipoDeConta fromDescricao(String descricao) { //O método "fromDescricao" faz o mapeamento da string (corrente ou poupanca) do Json para Enum
//        for (TipoDeConta tipoDeConta : TipoDeConta.values()) {
//            if (tipoDeConta.getDescricao().equalsIgnoreCase(descricao)) {
//                return tipoDeConta;
//            }
//        }
//        throw new IllegalArgumentException("Tipo de Conta: Descrição inválida: " + descricao);
//    }
//	
	
	

}
