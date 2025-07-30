package com.jaavexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.jaavexpress.models.User;
@EnableJpaRepositories
public interface UserRespository  extends JpaRepository<User, Long> {

	/* Optional<User> findByUserName(String userName); */


}
