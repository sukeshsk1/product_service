package com.javaexpress.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.javaexpress.dto.UserDto;
import com.javaexpress.exception.ResourceNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserRestIntegrationService {

	@Autowired
	private RestTemplate restTempalte;
	
	private static final String USER_SERVICE_URL = "http://localhost:8081/api/users/{userId}";
	
	public UserDto fetchUser(Long userId) {
		try {
			ResponseEntity<UserDto> responseEntity = 
					restTempalte.getForEntity(USER_SERVICE_URL, UserDto.class,userId);
			
			if(responseEntity.getStatusCode().is2xxSuccessful()) {
				UserDto responseUserDto = responseEntity.getBody();
				log.info("userDto {}",responseUserDto.getEmail());
				return responseUserDto;
			} else {
				throw new ResourceNotFoundException("Failed to fetch user Information");
			}
		}catch(Exception ex) {
			log.error("Error occurred while fetching user from external service",ex);
			throw new ResourceNotFoundException(ex.getMessage());
		}
	}
}
