package com.exception;

public class AuthenticationException extends Exception {
	private static final long serialVersionUID = 1L;
	String message;
	
	public AuthenticationException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
