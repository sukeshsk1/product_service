package com.javaexpress.product.service;

import java.util.List;

import com.javaexpress.product.dto.ProductRequestDTO;
import com.javaexpress.product.dto.ProductResponceDTO;

public interface ProductService {
 ProductResponceDTO addProduct(ProductRequestDTO request);
 ProductResponceDTO updateProduct(Long productId, ProductRequestDTO request);
 ProductResponceDTO getProductById(Long productId);
 List<ProductResponceDTO> getAllProducts();
 
}
