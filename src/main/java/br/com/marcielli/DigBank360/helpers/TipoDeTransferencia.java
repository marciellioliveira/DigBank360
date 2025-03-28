package br.com.marcielli.DigBank360.helpers;

import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum TipoDeTransferencia {
	
	PIX(1, "PIX"),
	TED(2, "TED"),
	DOC(3, "DOC");

	private final int cod;
	private final String tipoDeTransferencia;	

	public int getCod() {
		return cod;
	}

	public String getTipoDeTransferencia() {
		return tipoDeTransferencia;
	}
	
	public static String getTipoDeTransferenciaPorCodigo(int cod) {
		for(TipoDeTransferencia transferencia : TipoDeTransferencia.values()) {
			if(cod == transferencia.getCod()) {
				return transferencia.getTipoDeTransferencia();
			}
		}
		
		throw new IllegalArgumentException("Transferência: Código inválido: "+cod);
	}

}
