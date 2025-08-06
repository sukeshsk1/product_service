package com.javaexpress.product.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.product.dto.ProductRequestDTO;
import com.javaexpress.product.dto.ProductResponceDTO;
import com.javaexpress.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductService productService;

	@PostMapping
	public ProductResponceDTO createProduct(@RequestBody ProductRequestDTO request) {
		log.info("ProductController createProduct {}", request.getName());
		return productService.addProduct(request);
	}

	@GetMapping("/{productId}")
	public ProductResponceDTO getProduct(@PathVariable Long productId) {
		log.info("ProductController getProduct {}", productId);
		return productService.getProductById(productId);

	}

	@PutMapping("/{productId}")
	public ProductResponceDTO updateProduct(@PathVariable Long productId, @RequestBody ProductRequestDTO request) {
		log.info("ProductController updateProduct {}{}", productId, request.getName());
		return productService.updateProduct(productId, request);
	}

	@GetMapping
	public List<ProductResponceDTO> fetachAllProducts() {

		log.info("ProductController fetachAllProducts ");
		return productService.getAllProducts();

	}

}
