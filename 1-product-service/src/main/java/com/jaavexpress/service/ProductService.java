package com.jaavexpress.service;

import java.util.List;

import com.jaavexpress.dto.ProductRequestDTO;
import com.jaavexpress.dto.ProductResponceDTO;

public interface ProductService {
 ProductResponceDTO addProduct(ProductRequestDTO request);
 ProductResponceDTO updateProduct(Long productId, ProductRequestDTO request);
 ProductResponceDTO getProductById(Long productId);
 List<ProductResponceDTO> getAllProducts();
 
}
