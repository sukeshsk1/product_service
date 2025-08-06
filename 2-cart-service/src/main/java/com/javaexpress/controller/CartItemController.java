package com.javaexpress.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.dto.CartItemRequestDTO;
import com.javaexpress.dto.CartItemResponseDTO;
import com.javaexpress.service.CartServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/cart")
public class CartItemController {

	@Autowired
	private CartServiceImpl cartServiceImpl;
	
	@PostMapping
	public CartItemResponseDTO addToCart(@Valid @RequestBody CartItemRequestDTO request) {
		return cartServiceImpl.addToCart(request);
	}
	
    // Get all items in a user's cart
    @GetMapping("/{userId}")
    public ResponseEntity<List<CartItemResponseDTO>> getUserCart(@PathVariable Long userId) {
        List<CartItemResponseDTO> responseList = cartServiceImpl.getUserCart(userId);
        return ResponseEntity.ok(responseList);
    }
    
    
    // Update quantity of item in cart
    @PutMapping("/update")
    public ResponseEntity<CartItemResponseDTO> updateCartQuantity(@RequestBody CartItemRequestDTO request) {
        CartItemResponseDTO response = cartServiceImpl.updateQuantity(request);
        if (response == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(response);
    }
    
    @DeleteMapping("/{userId}/{productId}")
    public void removeItem(@PathVariable Long userId, @PathVariable Long productId) {
    	cartServiceImpl.removeItem(userId, productId);
    }

}














