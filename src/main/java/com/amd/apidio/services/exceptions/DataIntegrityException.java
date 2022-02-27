package com.amd.apidio.services.exceptions;

/* Essa é uma implementação personalizada de exceção tendo o meu tipo do meu pacote */

public class DataIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public DataIntegrityException(String msg) {
		super(msg);
	}

	public DataIntegrityException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
