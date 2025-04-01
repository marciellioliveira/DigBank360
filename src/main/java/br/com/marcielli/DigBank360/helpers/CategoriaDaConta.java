package br.com.marcielli.DigBank360.helpers;



public enum CategoriaDaConta {
	
	COMUM("COMUM"),
	SUPER("SUPER"),
	PREMIUM("PREMIUM");	
	
	private final String descricao;	
	

	public String getDescricao() {
		return descricao;
	}
	
	private CategoriaDaConta(String descricao) {
		this.descricao = descricao;
	}	
	

	
	

}
