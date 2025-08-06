package com.javaexpress.user.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.user.dto.CredentialDTO;
import com.javaexpress.user.dto.UserDTO;
import com.javaexpress.user.exception.UserNotFoundException;
import com.javaexpress.user.model.Credential;
import com.javaexpress.user.model.User;
import com.javaexpress.user.repository.UserRespository;
import com.javaexpress.user.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRespository userRespository;

	@Override
	public UserDTO save(UserDTO userDTO) {
		// TODO Auto-generated method stub
		
		CredentialDTO credentialDto = userDTO.getCredential();
		User user = new User();
		BeanUtils.copyProperties(userDTO, user);

		Credential credential = new Credential();
		BeanUtils.copyProperties(credentialDto, credential);

		// set bidirectional relationship
		credential.setUser(user);
		user.setCredential(credential);

		User dbUser = userRespository.save(user);
		Credential dbCredential = dbUser.getCredential();

		UserDTO userResponse = mapToDto(dbUser, dbCredential);

		return userResponse;

	}

	private UserDTO mapToDto(User dbUser, Credential dbCredential) {
		
		
		UserDTO userResponse = new UserDTO();
		BeanUtils.copyProperties(dbUser, userResponse);

		CredentialDTO credentialResponse = new CredentialDTO();
		BeanUtils.copyProperties(dbCredential, credentialResponse);

		userResponse.setCredential(credentialResponse);
		return userResponse;
	}

	
	/*
	 * public UserDTO mapToDTO(User dbUser) { UserDTO userResponse = new UserDTO();
	 * BeanUtils.copyProperties(dbUser, userResponse);
	 * 
	 * return userResponse;
	 * 
	 * }
	 */
	 

	@Override
	public UserDTO findBy(Long userId) {
		log.info("UserServiceImpl findById {}", userId);
		Optional<User> optional = userRespository.findById(userId);
		if (optional.isPresent()) {
			User dbUser = optional.get();
			 Credential dbCrdential = dbUser.getCredential();
			return mapToDto(dbUser,dbCrdential);
		} else {
			throw new UserNotFoundException("User Not found in database");

		}

	}

	@Override
	public List<UserDTO> findAll() {

		log.info("UserServiceImpl findAll ");
		return userRespository.findAll().stream().map(obj -> mapToDto(obj,obj.getCredential())).toList();
	}

	@Override
	public UserDTO update(Long userId, UserDTO userDto) {
		
		User dbUser = userRespository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User id not exits in db"));
		
		dbUser.setFirstName(userDto.getFirstName());
		dbUser.setLastName(userDto.getLastName());
		dbUser.setEmail(userDto.getEmail());
		dbUser.setPhone(userDto.getPhone());
		
		userRespository.save(dbUser);
		
		return mapToDto(dbUser, dbUser.getCredential());
	}

	@Override
	public UserDTO findByUsername(String username) {
		return userRespository.findByCredentialUsername(username)
				.map(user -> mapToDto(user, user.getCredential()))
				.orElseThrow(() -> new UserNotFoundException("Username not found in db"));
				
	}

}
