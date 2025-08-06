package com.javaexpress.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="cart_items")
@Setter
@Getter
public class CartItem {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Long userId;
	
	private Long productId;
	
	@Min(value = 1,message = "Quantity should be minimum 1")
	private Integer quantity;
	
	private LocalDateTime createdAt = LocalDateTime.now();
}
