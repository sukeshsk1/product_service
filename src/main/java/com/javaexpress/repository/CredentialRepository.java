package com.javaexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.models.Credential;
import com.javaexpress.models.Product;

public interface CredentialRepository extends JpaRepository<Credential, Long>{

}
