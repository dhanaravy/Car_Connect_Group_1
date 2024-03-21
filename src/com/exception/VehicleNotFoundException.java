package com.exception;

public class VehicleNotFoundException extends Exception {
	private static final long serialVersionUID = 1L;
	String message;
	
	public VehicleNotFoundException(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
