package exception.clientes;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UnsuportedMesNascimentoException  extends RuntimeException  {
	
	/**
	 * Exception para Mês de Nascimento inválido
	 */
	private static final long serialVersionUID = 1L;
	
	public UnsuportedMesNascimentoException(String ex) {
		super(ex);
	}

}
