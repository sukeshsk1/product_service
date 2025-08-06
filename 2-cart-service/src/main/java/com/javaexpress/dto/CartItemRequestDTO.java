package com.javaexpress.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CartItemRequestDTO {

	private Long userId;
	
	private Long productId;
	
	@Min(value = 1,message = "Quantity should be minimum 1")
	private Integer quantity;
}
