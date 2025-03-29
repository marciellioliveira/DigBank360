package exception.contas;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import exception.ExceptionResponse;
import exception.clientes.UnsuportedClientDataWrongException;
import exception.clientes.UnsuportedClientDontExistException;
import exception.clientes.UnsuportedNameException;

public class CustomizedResponseEntityExceptionHandlerContas extends ResponseEntityExceptionHandler {
	
	//Excessão genérica - 500 Internal Server Error
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Unsuported Conta não válida Exception - Customizada
	@ExceptionHandler(UnsuportedContaNotValidException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsContaNotValid(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	
	//Unsuported Saldo da conta é nulo Exception - Customizada
		@ExceptionHandler(UnsuportedContaSaldoNullException.class)
		public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsContaSaldoNuloValid(Exception ex, WebRequest request) {
			ExceptionResponse exceptionResponse = new ExceptionResponse(
					new Date(), ex.getMessage(), request.getDescription(false));
			
			return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
		}

}
