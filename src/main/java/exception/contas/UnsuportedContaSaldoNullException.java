package exception.contas;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedContaSaldoNullException extends RuntimeException  {
	
	/**
	 * Exception Erro saldo da conta é nulo
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuportedContaSaldoNullException(String ex) {
		super(ex);
	}

}
