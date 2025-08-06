package com.javaexpress.user.exception;

public class ResourceNotFoundException extends RuntimeException {
	
	public ResourceNotFoundException(String message) {
		super(message);
	}

}
