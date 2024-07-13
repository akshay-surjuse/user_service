package com.humancloud.task_management_tool.user_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.humancloud.task_management_tool.user_service.entity.Role;
import com.humancloud.task_management_tool.user_service.entity.User;
import com.humancloud.task_management_tool.user_service.servicei.UserServiceI;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping(value = "/user_service", consumes = "application/json", produces = "application/json")
public class UserController 
{
	@Autowired
	private UserServiceI service;
	
	@PostMapping(value = "/", consumes = "application/json", produces = "application/json")
	public ResponseEntity<User> saveUser(@RequestBody User user){
		User u = service.addUser(user);
		return new ResponseEntity<User>(u, HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/", produces = "application/json")
	public ResponseEntity<User> loginCheck(@RequestBody User u, HttpServletRequest request){
		User user = service.loginCheck(u.getUsername(), u.getPassword());
		HttpSession session = request.getSession(true);
		session.setAttribute("userId", user.getUserId());
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/{userId}", produces = "application/json")
	public ResponseEntity<User> getUserById(@PathVariable Integer userId){
		User user = service.getUserById(userId);
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PutMapping(value = "/", produces = "application/json", consumes = "application/json")
	public ResponseEntity<User> updateUser(@RequestBody User user, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		Integer userId = (Integer)session.getAttribute("userId");
		User updateUser = service.updateUserById(userId, user);
		return new ResponseEntity<User>(updateUser, HttpStatus.OK);
	}
	
	@PostMapping(value = "/role", produces = "application/json", consumes = "application/json")
	public ResponseEntity<Role> assignRoleToUser(@RequestBody Role role, HttpServletRequest request){
		HttpSession session = request.getSession(false);
		Integer userId = (Integer)session.getAttribute("userId");
		Role assignRoleToUser = service.assignRoleToUser(userId, role);
		return new ResponseEntity<Role>(assignRoleToUser, HttpStatus.OK);
	}
	
}
