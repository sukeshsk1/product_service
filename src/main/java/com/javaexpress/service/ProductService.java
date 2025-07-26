package com.javaexpress.service;

import java.util.List;

import com.javaexpress.dto.ProductRequestDTO;
import com.javaexpress.dto.ProductResponseDTO;

public interface ProductService {

	ProductResponseDTO addProduct(ProductRequestDTO request);
	
	ProductResponseDTO updateProduct(Long productId,ProductRequestDTO request);
	
	ProductResponseDTO getProductById(Long productId);
	
	List<ProductResponseDTO> getAllProducts();
}
