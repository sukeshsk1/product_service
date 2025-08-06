package com.javaexpress.user.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserDTO {
	private long userId;
	
	@Size(min=2,max=30,message="First name must be at least 3 characters")
	private String firstName;
	
	@Size(min=1,message="Lasr Name is required")
	private String lastName;
	
	@Email(message="Email Should be valid")
	private String email;
	
	@Pattern(regexp="^[0-9]{10}$",message="Phone number must be 10 digits")
	private String phone;
	@NotNull(message="credential is required")
	private CredentialDTO credential;
}
