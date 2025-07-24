package com.jaavexpress.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaavexpress.dto.ProductRequestDTO;
import com.jaavexpress.dto.ProductResponceDTO;
import com.jaavexpress.excption.ProductNotFoundException;
import com.jaavexpress.models.Product;
import com.jaavexpress.repository.ProductRepository;
import com.jaavexpress.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepository;

	@Override
	public ProductResponceDTO addProduct(ProductRequestDTO request) {
		log.info("ProductServiceImpl addProduct {}" ,request.getName());
		Product product = new Product();
		product.setName(request.getName());
		product.setDescription(request.getDescription());
		product.setPrice(request.getPrice());
		product.setBrand(request.getBrand());

		product.setStock(request.getStock());
		product.setStatus("ACTIVE");
		// BeanUtils.copyProperties(request, product);

		Product dbProduct = productRepository.save(product);

		return mapToDTO(dbProduct);
	}

	@Override
	public ProductResponceDTO updateProduct(Long productId, ProductRequestDTO request) {
		log.info("ProductServiceImpl updateProduct {} {}", productId ,request);

		Optional<Product> optional = productRepository.findById(productId);

		if (optional.isPresent()) {
			Product dbProduct = optional.get();
			BeanUtils.copyProperties(request, dbProduct);
			Product updatedProductDB= productRepository.save(dbProduct);//in dbProduct we have id field -update
			return mapToDTO(updatedProductDB);
			

		} else {
			throw new ProductNotFoundException("Product Not found in database");
		}
	
	}

	@Override
	public ProductResponceDTO getProductById(Long productId) {
		log.info("ProductServiceImpl getProductById {}", productId);
		Optional<Product> optional = productRepository.findById(productId);
		if (optional.isPresent()) {
			Product dbProduct = optional.get();
			return mapToDTO(dbProduct);
		} else {
			throw new ProductNotFoundException("Product Not found in database");
		}

	}

	@Override
	public List<ProductResponceDTO> getAllProducts() {

		log.info("ProductServiceImpl getAllProducts");

		return productRepository.findAll().stream().map(obj -> mapToDTO(obj)).toList();
	}

	public ProductResponceDTO mapToDTO(Product product) {
		ProductResponceDTO respose = new ProductResponceDTO();
		BeanUtils.copyProperties(product, respose);

		return respose;

	}

}
