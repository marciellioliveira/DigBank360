package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedNumeroException extends RuntimeException {
	
	/**
	 * Exception para Mês de Nascimento inválido
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuportedNumeroException(String ex) {
		super(ex);
	}

}
