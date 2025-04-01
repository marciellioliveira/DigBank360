package br.com.marcielli.DigBank360.exception;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ExceptionResponse implements Serializable {

	/**
	 * Retorna um Json com 3 campos.
	 */
	private static final long serialVersionUID = 1L;
	
	private Date timestamp;
	private String message;
	private String details;

	
	
}
