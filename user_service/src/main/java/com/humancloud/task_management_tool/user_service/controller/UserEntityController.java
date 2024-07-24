package com.humancloud.task_management_tool.user_service.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humancloud.task_management_tool.user_service.binding.AuthRequest;
import com.humancloud.task_management_tool.user_service.binding.AuthResponse;
import com.humancloud.task_management_tool.user_service.entity.Role;
import com.humancloud.task_management_tool.user_service.entity.UserEntity;
import com.humancloud.task_management_tool.user_service.service.UserEntityService;

@RestController
@RequestMapping(value = "/user_service")
@CrossOrigin
public class UserEntityController {
	
	private Logger logger = LoggerFactory.getLogger(UserEntityController.class);
	
	@Autowired
	private UserEntityService service;
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@PostMapping(value = "/register", consumes = "application/json", produces = "plain/text")
	public ResponseEntity<String> registerUser(@RequestBody UserEntity user) {
		String message = service.saveUser(user);
		return new ResponseEntity<String>(message, HttpStatus.CREATED);
	}
	
	@PostMapping(value = "/login",consumes = "application/json", produces = "application/json")
	public AuthResponse login(@RequestBody AuthRequest authRequest) {

		AuthResponse response = new AuthResponse();
		System.out.println("authrequest" + authRequest.getUsername() + authRequest.getPassword());
		UsernamePasswordAuthenticationToken token = 
			    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
		try {
			Authentication authenticate = authenticationManager.authenticate(token);
			String bearerToken = null;
			System.out.println(authenticate.isAuthenticated());
			if (authenticate.isAuthenticated()) {
				bearerToken = service.generateToken(authRequest.getUsername());
				response.setToken(bearerToken);
				response.setLoginValid("yes");
			}
		} catch (Exception e) {
			response.setToken("");
			response.setLoginValid("no");
			e.printStackTrace();
		}

		return response;
	}
	
	@GetMapping(value = "/get/{userId}", produces = "application/json")
	public ResponseEntity<UserEntity> getUserById(@PathVariable Integer userId){
		System.out.println("userId: " + userId + " *******************");
		UserEntity user = service.getUserById(userId);
		return new ResponseEntity<UserEntity>(user, HttpStatus.OK);
	}
	
	@PutMapping(value = "/update/{userId}", produces = "application/json", consumes = "application/json")
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
