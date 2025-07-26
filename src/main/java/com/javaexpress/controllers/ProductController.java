package com.javaexpress.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.ProductRequestDTO;
import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/products")
@Slf4j
public class ProductController {

	@Autowired
	//@Qualifier("")
	private ProductService productService;
	
	@PostMapping
	public ProductResponseDTO createProduct(@RequestBody ProductRequestDTO request) {
		log.info("ProductController createProduct {}",request.getName());
		return productService.addProduct(request);
	}
	
	@GetMapping("/{productId}") // 5
	public ProductResponseDTO getProduct(@PathVariable Long productId) {
		log.info("ProductController getProduct {}",productId);
		return productService.getProductById(productId);
	}
	
	@PutMapping("/{productId}")
	public ProductResponseDTO updateProduct(@PathVariable Long productId,
			@RequestBody ProductRequestDTO request) {
		log.info("ProductController updateProduct {} {}",productId,request.getName());
		return productService.updateProduct(productId, request);
	}
	
	@GetMapping
	public List<ProductResponseDTO> fetchAllProducts() {
		log.info("ProductController fetchAllProducts");
		return productService.getAllProducts();
	}
	
}
