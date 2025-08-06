package com.javaexpress.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javaexpress.user.dto.UserDTO;
import com.javaexpress.user.service.UserService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public UserDTO createUser(@Valid @RequestBody UserDTO userDTO) {
		log.info("UserController userDTO {}", userDTO);
		return userService.save(userDTO);
	}

	@GetMapping("/{userId}")
	public UserDTO getUserById(@PathVariable Long userId) {
		log.info("UserController getUserById {}", userId);
		return userService.findBy(userId);

	}

	@GetMapping
	public List<UserDTO> fetachALUsers() {

		log.info("UserController fetachALUsers ");
		return userService.findAll();

	}
	@GetMapping("/username/{username}")
	public UserDTO getUserByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
	
	@PutMapping("/{userId}")
	public UserDTO updateUser(@PathVariable Long userId,@RequestBody UserDTO input) {
		return userService.update(userId, input);
	}

}
