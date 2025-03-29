package exception.contas;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedContaNotValidException extends RuntimeException {
	
	/**
	 * Exception Conta inválida
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuportedContaNotValidException(String ex) {
		super(ex);
	}

}
