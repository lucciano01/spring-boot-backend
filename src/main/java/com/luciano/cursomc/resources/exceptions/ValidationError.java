package com.luciano.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors;

	public ValidationError(Integer statusHttp, String msg, Long timeStamp) {
		super(statusHttp, msg, timeStamp);
		
	}

	public List<FieldMessage> getErrors() {
		if(errors == null) {
			errors = new ArrayList<>();
		}
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.getErrors().add(new FieldMessage(fieldName, message));
	}

	

}
