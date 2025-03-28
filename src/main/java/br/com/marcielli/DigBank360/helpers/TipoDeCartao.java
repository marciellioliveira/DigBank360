package br.com.marcielli.DigBank360.helpers;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoDeCartao {
	
	CARTAO_CREDITO(1, "Cartão de Crédito"),
	CARTAO_DEBITO(2, "Cartão de Débito");
	
	private final int codigo;	
	private final String descricao;	

	public int getCodigo() {
		return codigo;
	}

	public String getDescricao() {
		return descricao;
	}
	
	public static String getTipoDeCartaoPorCodigo(int cod) {
		for(TipoDeCartao tipoDeCartao : TipoDeCartao.values()) {
			if(cod == tipoDeCartao.getCodigo()) {
				return tipoDeCartao.getDescricao();
			}
		}
		
		throw new IllegalArgumentException("Tipo de Cartão: Código inválido: "+cod);
	}
	

}
