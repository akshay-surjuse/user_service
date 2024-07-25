package com.humancloud.task_management_tool.user_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humancloud.task_management_tool.user_service.entity.Role;
import com.humancloud.task_management_tool.user_service.entity.UserEntity;
import com.humancloud.task_management_tool.user_service.servicei.UserEntityServiceI;

@RestController
@RequestMapping(value = "/user_service")
@CrossOrigin
public class UserEntityController {
	
	@Autowired
	private UserEntityServiceI service;
	
	@GetMapping(value = "/get/{userId}", produces = "application/json")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Integer userId){
		System.out.println("userId: " + userId + " *******************");
		UserEntity user = service.getUserById(userId);
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}
	
	@PutMapping(value = "/{userId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<UserEntity> updateUser(@PathVariable Integer userId, @RequestBody UserEntity user){
		UserEntity updateUser = service.updateUserById(userId, user);
		return new ResponseEntity<UserEntity>(updateUser, HttpStatus.OK);
	}
	
	@PutMapping(value = "/assign/{userId}", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> assignRoleToUser(@PathVariable Integer userId, @RequestBody List<Role> roles){
		String message = service.assignRoleToUser(userId, roles);
		return new ResponseEntity<String>(message, HttpStatus.OK);
	}
}
