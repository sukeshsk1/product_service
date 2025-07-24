package com.jaavexpress.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaavexpress.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
