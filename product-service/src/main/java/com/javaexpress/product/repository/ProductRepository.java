package com.javaexpress.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.javaexpress.product.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

}
