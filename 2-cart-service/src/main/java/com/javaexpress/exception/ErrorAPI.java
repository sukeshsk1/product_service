package com.javaexpress.exception;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorAPI {

	private String message;
	private String status;
	private String error;
	private LocalDateTime localDateTime;
}
