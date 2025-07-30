package com.jaavexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaavexpress.models.Credential;

public interface CredentialRespository   extends JpaRepository<Credential, Long>{

}
