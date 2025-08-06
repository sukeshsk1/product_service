package com.javaexpress.user.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;


@Entity
@Data
@AllArgsConstructor
public class Credential {
	
	public Credential() {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long credentialId;
	
	
	@Column(unique = true)
	private String username;
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column(name="role")
	private RoleBasedAuthority roleBasedAuthority;
	
	@OneToOne
	
	@JoinColumn(name="user_id",unique=true)
	private User user;
	
	
	
	
	

}
