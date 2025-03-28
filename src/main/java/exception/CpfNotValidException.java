package exception;

public class CpfNotValidException extends RuntimeException {
	
	public CpfNotValidException(String cpf) {		
        super(String.format("CPF inválido: "+cpf));
    }

}
