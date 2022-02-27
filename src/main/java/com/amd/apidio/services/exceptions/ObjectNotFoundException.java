package com.amd.apidio.services.exceptions;

/* Essa é uma implementação personalizada de exceção tendo o meu tipo do meu pacote */

public class ObjectNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}

	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
}
