package com.javaexpress.service;

import java.util.List;

import com.javaexpress.dto.UserDto;

public interface UserService {

	UserDto save(UserDto userDto);
	UserDto findBy(Long userId);
	List<UserDto> findAll();
	UserDto update(Long userId,UserDto userDto);
	UserDto findByUsername(String username);
	
}
