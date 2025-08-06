package com.javaexpress.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.user.model.Credential;

public interface CredentialRespository   extends JpaRepository<Credential, Long>{

}
