package com.example.fullstackapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// fazer uso de uma annotation para definir o papel que esta classe
// assumirá
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
	// criar o serial version
	private static final long serialVersionUID = 1L;
	public ResourceNotFoundException(String message) {
		 super(message);
	}
}
