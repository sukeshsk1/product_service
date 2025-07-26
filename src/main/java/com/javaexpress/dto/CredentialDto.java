package com.javaexpress.dto;

import java.math.BigDecimal;

import com.javaexpress.models.RoleBasedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CredentialDto {

	private String username;
	private String password;
	private RoleBasedAuthority roleBasedAuthority;
}
