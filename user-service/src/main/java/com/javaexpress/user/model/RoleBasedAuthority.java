package com.javaexpress.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter


public enum RoleBasedAuthority {
	ROLE_USER("USER"),
	ROLE_ADMIN("ADMIN");
	
	private final String role;

}
