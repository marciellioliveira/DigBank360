package br.com.marcielli.DigBank360.exception.clientes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedAnoNascimentoException extends RuntimeException  {
	
	/**
	 * Exception para Ano de Nascimento inválido
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuportedAnoNascimentoException(String ex) {
		super(ex);
	}

}
