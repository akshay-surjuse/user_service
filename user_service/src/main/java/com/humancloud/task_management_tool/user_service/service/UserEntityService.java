package com.humancloud.task_management_tool.user_service.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.humancloud.task_management_tool.user_service.entity.Role;
import com.humancloud.task_management_tool.user_service.entity.UserEntity;
import com.humancloud.task_management_tool.user_service.exception.DuplicateUsernameException;
import com.humancloud.task_management_tool.user_service.exception.UserNotFoundException;
import com.humancloud.task_management_tool.user_service.repository.UserEntityRepository;

@Service
public class UserEntityService {
	
	@Autowired
	private UserEntityRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	public String saveUser(UserEntity user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		UserEntity userEntity = repository.findByUsername(user.getUsername());
		if(userEntity==null) {
	        repository.save(user);
	        return "user added to the system";
		}else {
			throw new DuplicateUsernameException("User record with given userId is already present");
		}
    }
	
	public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

	public UserEntity getUserById(Integer userId) {
		Optional<UserEntity> optional = repository.findById(userId);
		if(optional.isPresent())
			return optional.get();
		else
			throw new UserNotFoundException("UserEntity recod with given userId is not present in database.");
	}

	public UserEntity updateUserById(Integer userId, UserEntity user) {
		Optional<UserEntity> optional = repository.findById(userId);
		if(optional.isPresent())
			return repository.save(user);
		else
			throw new UserNotFoundException("User record not present in database");
	}


	public String assignRoleToUser(Integer userId, List<Role> roles) {
		Optional<UserEntity> optional = repository.findById(userId);
		if(optional.isPresent()) {
			UserEntity userEntity = optional.get();
			userEntity.setRole(roles);
			repository.save(userEntity);
			return "Role has been assgined to user";
		}else {
			throw new UserNotFoundException("User record is not found in database");
		}
	}
}
