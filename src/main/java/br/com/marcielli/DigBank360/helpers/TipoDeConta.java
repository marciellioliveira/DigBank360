package br.com.marcielli.DigBank360.helpers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoDeConta {
	
	CORRENTE(1,"CORRENTE"),
	POUPANCA(2, "POUPANCA");
	
	
	private final int codigo;	
	private final String descricao;
	

	public int getCodigo() {
		return codigo;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	@JsonValue //Define o valor da serialização
	public String getDescricaoForJson() { //Garante que ao serializar um Enum voltando para o Json, o valor da descricao será enviado para o json
		return this.descricao;
	}

	@JsonCreator
	public static TipoDeConta fromDescricao(String descricao) { //O método "fromDescricao" faz o mapeamento da string (corrente ou poupanca) do Json para Enum
        for (TipoDeConta tipoDeConta : TipoDeConta.values()) {
            if (tipoDeConta.getDescricao().equalsIgnoreCase(descricao)) {
                return tipoDeConta;
            }
        }
        throw new IllegalArgumentException("Tipo de Conta: Descrição inválida: " + descricao);
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
