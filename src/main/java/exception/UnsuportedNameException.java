package exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedNameException extends RuntimeException {

	/**
	 * Exception para Nome inválido
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuportedNameException(String ex) {
		super(ex);
	}

}
