package com.java.express.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Long userId;
	private BigDecimal totalPrice;
	private String status;
	
	private LocalDateTime placedAt=LocalDateTime.now();
	
	@OneToMany(mappedBy = "order" ,cascade = CascadeType.ALL)
	private List<OrderItem>items=new ArrayList<>();
}
