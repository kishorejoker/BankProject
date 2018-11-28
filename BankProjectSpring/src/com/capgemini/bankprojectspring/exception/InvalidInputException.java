package com.capgemini.bankprojectspring.exception;

@SuppressWarnings("serial")
public class InvalidInputException extends RuntimeException{
	private String message;
	
	public InvalidInputException(String message) {
		super(message);
	}

	public String getMessage() {
		return message;
	}

	@Override
	public String toString() {
		return "InvalidInputException [message=" + message + "]";
	}

}
