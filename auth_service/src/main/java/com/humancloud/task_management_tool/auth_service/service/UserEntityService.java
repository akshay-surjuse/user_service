package com.humancloud.task_management_tool.auth_service.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.humancloud.task_management_tool.auth_service.entity.UserEntity;
import com.humancloud.task_management_tool.auth_service.exception.DuplicateUsernameException;
import com.humancloud.task_management_tool.auth_service.repository.UserEntityRepository;

@Service
public class UserEntityService {
	
	@Autowired
	private UserEntityRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private JwtService jwtService;
	
	public String saveUser(UserEntity user) {
		UserEntity userEntity = repository.findByUsername(user.getUsername());
		if(userEntity==null) {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
	        repository.save(user);
	        return "user added to the system";
		}else {
			throw new DuplicateUsernameException("User record with given userId is already present");
		}
    }
	
	public String generateToken(String username, UserEntity userEntity) {
        return jwtService.generateToken(username, userEntity);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }
    
    public UserEntity getUserEntity(String username) {
    	return repository.findByUsername(username);
    }
}
