package exception;

public class NomeNotValidException  extends RuntimeException {
	
	public NomeNotValidException(String nome) {		
        super(String.format("Nome inválido: "+nome));
    }

}
