package com.javaexpress.product.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductRequestDTO {
	
	private String name;
	private String description;
	private BigDecimal price;
	private Integer stock;
	private String brand;
	

}
