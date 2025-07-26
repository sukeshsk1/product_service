package com.javaexpress.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.ProductRequestDTO;
import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.exeption.ProductNotFoundException;
import com.javaexpress.models.Product;
import com.javaexpress.repository.ProductRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ProductResponseDTO addProduct(ProductRequestDTO request) {
		log.info("ProductServiceImpl addProduct");
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setStatus("ACTIVE");
		product.setBrand(request.getBrand());
		product.setStock(request.getStock());
		
		//BeanUtils.copyProperties(request, product);
		
		Product dbProduct = productRepository.save(product);
		
		return mapToDTO(dbProduct);
	}

	@Override
	public ProductResponseDTO updateProduct(Long productId, ProductRequestDTO request) {
		Optional<Product> optional = productRepository.findById(productId);
		if(optional.isPresent()) {
			Product dbProduct = optional.get();
			BeanUtils.copyProperties(request, dbProduct);
			Product updatedProductDB = productRepository.save(dbProduct); // in dbProduct we have id field - update
			return mapToDTO(updatedProductDB);
		} else {
			throw new ProductNotFoundException("Product Not found in database");
		}
	}

	@Override
	public ProductResponseDTO getProductById(Long productId) {
		log.info("ProductServiceImpl getProductById {}",productId);
		Optional<Product> optional = productRepository.findById(productId);
		if(optional.isPresent()) {
			Product dbProduct = optional.get();
			return mapToDTO(dbProduct);
		} else {
			throw new ProductNotFoundException("Product Not found in database");
		}
	}

	@Override
	public List<ProductResponseDTO> getAllProducts() {
		log.info("getAllProducts");
		return productRepository.findAll()
						.stream()
						.map(obj -> mapToDTO(obj))
						.toList();
	}

	public ProductResponseDTO mapToDTO(Product product) {
		ProductResponseDTO response = new ProductResponseDTO();
		BeanUtils.copyProperties(product, response);
		return response;
	}
	
}
