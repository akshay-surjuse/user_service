package com.humancloud.task_management_tool.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humancloud.task_management_tool.auth_service.binding.AuthRequest;
import com.humancloud.task_management_tool.auth_service.binding.AuthResponse;
import com.humancloud.task_management_tool.auth_service.entity.UserEntity;
import com.humancloud.task_management_tool.auth_service.service.UserEntityService;

@RestController
@RequestMapping(value = "/auth_service")
@CrossOrigin
public class UserEntityController {
	
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
		UsernamePasswordAuthenticationToken usernameAndPasswordAuthenticationToken = 
			    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
		try {
			Authentication authenticate = authenticationManager.authenticate(usernameAndPasswordAuthenticationToken);
			String bearerToken = null;
			System.out.println(authenticate.isAuthenticated());
			if (authenticate.isAuthenticated()) {
				UserEntity userEntity = service.getUserEntity(authRequest.getUsername());
				bearerToken = service.generateToken(authRequest.getUsername(), userEntity);
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
}
