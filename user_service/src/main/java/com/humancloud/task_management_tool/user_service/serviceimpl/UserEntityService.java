package com.humancloud.task_management_tool.user_service.serviceimpl;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humancloud.task_management_tool.user_service.entity.Role;
import com.humancloud.task_management_tool.user_service.entity.UserEntity;
import com.humancloud.task_management_tool.user_service.exception.UserNotFoundException;
import com.humancloud.task_management_tool.user_service.repository.UserEntityRepository;
import com.humancloud.task_management_tool.user_service.servicei.UserEntityServiceI;

@Service
public class UserEntityService implements UserEntityServiceI{
	
	@Autowired
	private UserEntityRepository repository;
	
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
