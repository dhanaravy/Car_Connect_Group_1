package com.exception;

public class DatabaseConnectionException extends Exception{
	private static final long serialVersionUID = 1L;
	String message;
	
	public DatabaseConnectionException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
