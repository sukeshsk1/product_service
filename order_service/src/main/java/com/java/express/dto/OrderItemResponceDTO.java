package com.java.express.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderItemResponceDTO {
	
	private Long productId;
	private Integer quantity;
	private BigDecimal price;
	
	private ProductResponceDTO product;

}
