package com.javaexpress.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CartItemRequestDTO;
import com.javaexpress.dto.CartItemResponseDTO;
import com.javaexpress.dto.ProductResponseDTO;
import com.javaexpress.dto.UserDto;
import com.javaexpress.model.CartItem;
import com.javaexpress.repository.CartItemRepository;

import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CartServiceImpl {

	@Autowired
	private CartItemRepository cartItemRepository;
	
	@Autowired
	private UserRestIntegrationService userRestIntegrationService;
	
	@Autowired
	private ProductRestIntegrationService productRestIntegrationService;
	
	public CartItemResponseDTO addToCart(CartItemRequestDTO request) {
		Long userId = request.getUserId();
		Long productId = request.getProductId();
		
		UserDto userDto = userRestIntegrationService.fetchUser(userId);
		ProductResponseDTO product = productRestIntegrationService.fetchProduct(productId);
		// TODO : students - PRODUCT 
		
		CartItem cartItem = new CartItem();
		BeanUtils.copyProperties(request, cartItem);
		CartItem dbCartItem = cartItemRepository.save(cartItem);
		
		return mapToDto(dbCartItem, userDto,product);
	}
	
	public CartItemResponseDTO mapToDto(CartItem dbCartItem,UserDto user,ProductResponseDTO product) {
		CartItemResponseDTO response = new CartItemResponseDTO();
		BeanUtils.copyProperties(dbCartItem, response);
		response.setUser(user);
		response.setProduct(product);
		return response;
	}
	
	public CartItemResponseDTO updateQuantity(CartItemRequestDTO request) {
	
        Optional<CartItem> optionalCartItem = cartItemRepository.findByUserIdAndProductId(
                request.getUserId(), request.getProductId());

        if (optionalCartItem.isPresent()) {
            CartItem cartItem = optionalCartItem.get();
            cartItem.setQuantity(request.getQuantity());
            CartItem updatedItem = cartItemRepository.save(cartItem);

            UserDto userDto = userRestIntegrationService.fetchUser(request.getUserId());
            ProductResponseDTO product = productRestIntegrationService.fetchProduct(request.getProductId());

            return mapToDto(updatedItem, userDto, product);
        } else {
            log.warn("Cart item not found for userId: {}, productId: {}", request.getUserId(), request.getProductId());
            return null; // or throw an exception
        }
	}
	
	public List<CartItemResponseDTO> getUserCart(Long userId) {
		UserDto userDto = userRestIntegrationService.fetchUser(userId);
        List<CartItem> cartItems = cartItemRepository.findByUserId(userId);

        return cartItems.stream().map(item -> {
            ProductResponseDTO product = productRestIntegrationService.fetchProduct(item.getProductId());
            return mapToDto(item, userDto, product);
        }).collect(Collectors.toList());
		}
	
	@Transactional
	public void removeItem(Long userId,Long productId) {
		cartItemRepository.deleteByUserIdAndProductId(userId, productId);
	}
	
	
	
}
