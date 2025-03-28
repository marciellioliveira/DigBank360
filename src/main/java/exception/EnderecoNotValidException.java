package exception;

public class EnderecoNotValidException extends RuntimeException{
	
	public EnderecoNotValidException(String cep) {		
        super(String.format("CEP inválido: "+cep));
    }
}
