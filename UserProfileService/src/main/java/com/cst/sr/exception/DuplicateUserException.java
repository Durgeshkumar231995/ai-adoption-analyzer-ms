package com.cst.sr.exception;

public class DuplicateUserException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	public String message;

	public DuplicateUserException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
