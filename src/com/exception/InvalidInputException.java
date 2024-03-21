package com.exception;

public class InvalidInputException extends Exception{
	private static final long serialVersionUID = 1L;
	String message;
	
	public InvalidInputException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
