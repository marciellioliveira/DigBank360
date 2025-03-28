package br.com.marcielli.DigBank360.helpers;


import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CategoriaDaConta {
	
	COMUM(1, "Categoria Comum"),
	SUPER(2, "Categoria Super"),
	PREMIUM(3,"Categoria Premium");
	
	private final int cod;	
	private final String categoria;	

	public int getCod() {
		return cod;
	}

	public String getCategoria() {
		return categoria;
	}
	
	public static String getCategoriaPorCodigo(int cod) {
		for(CategoriaDaConta categoria: CategoriaDaConta.values()) {
			if(categoria.getCod() == cod) {
				return categoria.getCategoria();
			}
		}
		
		throw new IllegalArgumentException("Categoria da Conta: Código inválido: "+cod);
	}
}
