package com.humancloud.task_management_tool.user_service.config;

import java.util.Collections;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.humancloud.task_management_tool.user_service.entity.UserEntity;
import com.humancloud.task_management_tool.user_service.repository.UserEntityRepository;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserEntityRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		Optional<UserEntity> optional = repository.findByUsername(username);
//        UserEntity userEntity = optional.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
//        return new CustomUserDetails(userEntity);
		UserEntity userEntity = repository.findByUsername(username);
		return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
	}
}
