package com.exception;

public class InvalidCredentialsException extends Exception{
	private static final long serialVersionUID = 1L;
	String message;
	
	public InvalidCredentialsException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}