package com.javaexpress.user.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.javaexpress.user.model.User;
@EnableJpaRepositories
public interface UserRespository  extends JpaRepository<User, Long> {

	Optional<User> findByCredentialUsername(String username);

	/* Optional<User> findByUserName(String userName); */


}
