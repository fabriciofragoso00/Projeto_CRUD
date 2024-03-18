package com.example.fullstackapp.exceptions;

import java.util.Date;

// esta é classe onde estão definidas propriedades que - eventualemente -
// possam ser referenciadas para um mlehor detalhamento de infos de uma
// exceção ocorrida
public class ErrorDetails {
	// definir alguns atributos
	private Date timestamp;
	private String message;
	private String details;
	
	// construtor
	public ErrorDetails(Date timestamp, String message, String details) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.details = details;
	}

	// métodos get
	public Date getTimestamp() {
		return timestamp;
	}

	public String getMessage() {
		return message;
	}	

	public String getDetails() {
		return details;
	}		
	
}
