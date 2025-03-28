package br.com.marcielli.DigBank360.helpers;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoDeConta {
	
	CONTA_CORRENTE(1,"Conta Corrente"),
	CONTA_POUPANCA(2, "Conta Poupança");
	
	
	private final int codigo;	
	private final String descricao;
	

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	
	public static String getTipoDeContaPorCodigo(int cod) {
		for(TipoDeConta tipoDeConta : TipoDeConta.values()) {
			if(cod == tipoDeConta.getCodigo()) {
				return tipoDeConta.getDescricao();
			}
		}
		
		throw new IllegalArgumentException("Tipo de Conta: Código inválido: "+cod);

	}

}
