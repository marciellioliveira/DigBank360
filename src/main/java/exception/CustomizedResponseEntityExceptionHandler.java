package exception;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	//Excessão genérica - 500 Internal Server Error
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ExceptionResponse> handleAllExceptions(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//Unsuported Name Exception - Customizada
	@ExceptionHandler(UnsuportedNameException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsName(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported ano de Nascimento Exception - Customizada
	@ExceptionHandler(UnsuportedAnoNascimentoException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsAnoNasc(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported Mês de Nascimento Exception - Customizada
	@ExceptionHandler(UnsuportedMesNascimentoException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsMesNasc(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported Dia de Nascimento Exception - Customizada
	@ExceptionHandler(UnsuportedDiaNascimentoException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsDiaNasc(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported Cep Exception - Customizada
	@ExceptionHandler(UnsuportedCepException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsCep(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported Estado Exception - Customizada
	@ExceptionHandler(UnsuportedEstadoException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsEstado(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported Cidade Exception - Customizada
	@ExceptionHandler(UnsuportedCidadeException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsCidade(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported Bairro Exception - Customizada
	@ExceptionHandler(UnsuportedBairroException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsBairro(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported Rua Exception - Customizada
	@ExceptionHandler(UnsuportedRuaException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsRua(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported Numero Exception - Customizada
	@ExceptionHandler(UnsuportedNumeroException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsNumero(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	
	//Unsuported Complemento Exception - Customizada
	@ExceptionHandler(UnsuportedComplementoException.class)
	public final ResponseEntity<ExceptionResponse> handleBadRequestExceptionsComplemento(Exception ex, WebRequest request) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(
				new Date(), ex.getMessage(), request.getDescription(false));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
