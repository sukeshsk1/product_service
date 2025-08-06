package com.java.express.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.Data;
@Data
public class OrderResponseDTO {
	private Long orderId;
	private Long userId;
	private BigDecimal totalPrice;
	private String status;
	
	private LocalDateTime placeAt;
	
	private List<OrderItemResponceDTO> items;
	private UserDTO  user;
	
	

}
