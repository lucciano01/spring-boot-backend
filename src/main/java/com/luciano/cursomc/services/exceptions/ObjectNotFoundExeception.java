package com.luciano.cursomc.services.exceptions;

public class ObjectNotFoundExeception extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundExeception(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundExeception(String msg, Throwable cause) {
		super(msg,cause);
	}

}
