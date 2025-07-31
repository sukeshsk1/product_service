package com.jaavexpress.dto;

import com.jaavexpress.models.RoleBasedAuthority;

import lombok.Data;
@Data
public class CredentialDTO {
	
	private String username;
	//private String password;
	
	
	
	private RoleBasedAuthority roleBasedAuthority;
	
	

}
