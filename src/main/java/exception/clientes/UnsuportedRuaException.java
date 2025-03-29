package exception.clientes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedRuaException extends RuntimeException {
	
	/**
	 * Exception para Mês de Nascimento inválido
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuportedRuaException(String ex) {
		super(ex);
	}

}
