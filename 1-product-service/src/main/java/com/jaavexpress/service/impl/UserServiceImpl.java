package com.jaavexpress.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaavexpress.dto.CredentialDTO;
import com.jaavexpress.dto.UserDTO;
import com.jaavexpress.excption.UserNotFoundException;
import com.jaavexpress.models.Credential;
import com.jaavexpress.models.User;
import com.jaavexpress.repository.UserRespository;
import com.jaavexpress.service.UserService;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserDTO findByUsername(String username) {
		return null;
		// TODO Auto-generated method stub
		
		/*
		 * Optional<User> user = userRespository.findByUserName(userName);
		 * if(user.isPresent()) { User dbUser=user.get(); Credential dbCredential=
		 * dbUser.getCredential(); return mapToDto(dbUser, dbCredential); }else { throw
		 * new UserNotFoundException("User Not found in database"); }
		 */
		
		
	}

}
