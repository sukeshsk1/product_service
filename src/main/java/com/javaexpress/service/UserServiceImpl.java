package com.javaexpress.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javaexpress.dto.CredentialDto;
import com.javaexpress.dto.UserDto;
import com.javaexpress.models.Credential;
import com.javaexpress.models.User;
import com.javaexpress.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDto save(UserDto userDto) {
		// TODO Auto-generated method stub
		CredentialDto credentialDto = userDto.getCredential();
		User user = new User();
		BeanUtils.copyProperties(userDto, user);
		
		Credential credential = new Credential();
		BeanUtils.copyProperties(credentialDto, credential);
		
		// set bidirectional relationship
		credential.setUser(user);
		user.setCredential(credential);
		
		User dbUser = userRepository.save(user);
		Credential dbCredential = dbUser.getCredential();
		
		UserDto userResponse = mapToDto(dbUser, dbCredential);
		
		return userResponse;
	}

	private UserDto mapToDto(User dbUser, Credential dbCredential) {
		UserDto userResponse = new UserDto();
		BeanUtils.copyProperties(dbUser, userResponse);
		
		CredentialDto credentailResponse = new CredentialDto();
		BeanUtils.copyProperties(dbCredential, credentailResponse);
		
		userResponse.setCredential(credentailResponse);
		return userResponse;
	}

	@Override
	public UserDto findBy(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDto> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto update(Long userId, UserDto userDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDto findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
