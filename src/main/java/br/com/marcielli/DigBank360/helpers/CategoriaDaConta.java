package br.com.marcielli.DigBank360.helpers;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CategoriaDaConta {
	
	COMUM("COMUM"),
	SUPER("SUPER"),
	PREMIUM("PREMIUM");	
	
	private final String descricao;		
	
	private CategoriaDaConta(String descricao) {
		this.descricao = descricao;
	}	
	
	@JsonValue
	public String getDescricao() {
		return descricao;
	}
	
	@JsonCreator
    public static TipoDeConta fromString(String descricao) {
        for (TipoDeConta tipo : TipoDeConta.values()) {
            if (tipo.name().equalsIgnoreCase(descricao)) {
                return tipo;
            }
        }
        return null;  // Ou lançar uma exceção se o valor for inválido
    }
	
	@Override
	public String toString() {
		return descricao;		
	}
	

	
	

}
