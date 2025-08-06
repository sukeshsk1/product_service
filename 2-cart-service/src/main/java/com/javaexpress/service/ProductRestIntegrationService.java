package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductRestIntegrationService {

	@Autowired
	private RestTemplate restTempalte;
	
	private static final String PRODUCT_SERVICE_URL = "http://localhost:8082/products/{productId}";
	
	public ProductResponseDTO fetchProduct(Long productId) {
		try {
			ResponseEntity<ProductResponseDTO> responseEntity = 
					restTempalte.getForEntity(PRODUCT_SERVICE_URL, ProductResponseDTO.class,productId);
			
			if(responseEntity.getStatusCode().is2xxSuccessful()) {
				ProductResponseDTO response = responseEntity.getBody();
				return response;
			} else {
				throw new ResourceNotFoundException("Failed to fetch user Information");
			}
		}catch(Exception ex) {
			log.error("Error occurred while fetching user from external service",ex);
			throw new ResourceNotFoundException(ex.getMessage());
		}
	}
}
