package exception;

public class DataNascNotValidException extends RuntimeException  {

	public DataNascNotValidException(String dataNascimento) {		
        super(String.format("Data de Nascimento inválida: "+dataNascimento));
    }
}
