package com.lingotutor.userservice.error;

public class GenericError extends AbstractError {
	public String error;
	public GenericError(String error, String description) {
		super(description);
		this.error = error;
	}
}
