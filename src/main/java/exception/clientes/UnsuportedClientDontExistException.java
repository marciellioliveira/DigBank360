package exception.clientes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedClientDontExistException extends RuntimeException  {
	
	/**
	 * Exception Cliente não existe. Precisa ter um cliente primeiro para cadastrar uma conta.
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuportedClientDontExistException(String ex) {
		super(ex);
	}

}
