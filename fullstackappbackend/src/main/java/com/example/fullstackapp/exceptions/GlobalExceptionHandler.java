package com.example.fullstackapp.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// indicar a annotation adequada para que a classe assuma o "papel" de 
// "gerenciadora" de ocorrencias de exceção

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
	
	// referenciar a annotation @ExceptionHandler - exceção lançada se algum recurso estabelecido na base
	// for buscado e não for encontrado
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundException(ResourceNotFoundException ex, WebRequest request){
		// faz a referencia a classe ErrorDetails para lidar com suas propriedades
		ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), 
				request.getDescription(false));
		
		// estabelecer a expressão de retorno
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globeExceptionHandler(ResourceNotFoundException ex, WebRequest request){
		// faz a referencia a classe ErrorDetails para lidar com suas propriedades
				ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), 
						request.getDescription(false));
				
				// estabelecer a expressão de retorno
				return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
