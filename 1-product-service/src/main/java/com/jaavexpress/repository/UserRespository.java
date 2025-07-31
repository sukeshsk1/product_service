package com.jaavexpress.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jaavexpress.models.User;

@EnableJpaRepositories
public interface UserRespository extends JpaRepository<User, Long> {

	/* Optional<User> findByUserName(String userName); */

	// DSL
	// select * from user where emailId?

	Optional<User> findByEmail(String emailId);

	Optional<User> findByEmailOrPhone(String emailId, String phone);

	Optional<User> findByCredentialUsername(String username);

}
