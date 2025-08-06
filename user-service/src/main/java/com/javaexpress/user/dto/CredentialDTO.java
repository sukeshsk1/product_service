package com.javaexpress.user.dto;

import com.javaexpress.user.model.RoleBasedAuthority;

import lombok.Data;
@Data
public class CredentialDTO {
	
	private String username;
	//private String password;
	
	
	
	private RoleBasedAuthority roleBasedAuthority;
	
	

}
