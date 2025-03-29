package exception.clientes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedClientDataWrongException extends RuntimeException   {
	/**
	 * Exception Erro ao cadastrar um cliente. Dados errados.
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuportedClientDataWrongException(String ex) {
		super(ex);
	}
}
