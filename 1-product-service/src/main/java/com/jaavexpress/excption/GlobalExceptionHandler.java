package com.jaavexpress.excption;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class GlobalExceptionHandler {
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ErrorAPI> handleException(HttpMessageNotReadableException ex) {
		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setLocalDateTime(LocalDateTime.now());
		errorAPI.setMessage("Malformed JSON Data ");
		errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorAPI.setError(ex.getMessage());
		return new ResponseEntity<>(errorAPI,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ResponseEntity<ErrorAPI> handleException(ProductNotFoundException ex) {
		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setLocalDateTime(LocalDateTime.now());
		errorAPI.setMessage("Validation Error");
		errorAPI.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
		errorAPI.setError(ex.getMessage());
		return new ResponseEntity<>(errorAPI,HttpStatus.BAD_REQUEST);
	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorAPI> handleException(Exception ex) {
		ErrorAPI errorAPI = new ErrorAPI();
		errorAPI.setLocalDateTime(LocalDateTime.now());
		errorAPI.setMessage("Something went wrong");
		errorAPI.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
		errorAPI.setError(ex.getMessage());
		return new ResponseEntity<>(errorAPI,HttpStatus.INTERNAL_SERVER_ERROR);
	}


}
