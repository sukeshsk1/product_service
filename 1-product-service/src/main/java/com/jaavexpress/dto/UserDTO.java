package com.jaavexpress.dto;

import lombok.Data;

@Data
public class UserDTO {
	private long userId;
	private String firstName;
	private String lastName;
	private String email;
	private String phone;
	
	private CredentialDTO credential;
}
