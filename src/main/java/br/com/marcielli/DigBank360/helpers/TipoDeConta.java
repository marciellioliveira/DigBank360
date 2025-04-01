package br.com.marcielli.DigBank360.helpers;

public enum TipoDeConta {
	
	CORRENTE("CORRENTE"),
	POUPANCA("POUPANCA");
	
	private final String descricao;

	public String getDescricao() {
		return descricao;
	}
	
	private TipoDeConta(String descricao) {
		
		this.descricao = descricao;
	}

}
