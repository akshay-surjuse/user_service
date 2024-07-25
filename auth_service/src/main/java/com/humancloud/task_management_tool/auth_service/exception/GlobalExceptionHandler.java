package com.humancloud.task_management_tool.auth_service.exception;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(DuplicateUsernameException.class)
	public ResponseEntity<Response> duplicateUsernameException(DuplicateUsernameException e, HttpServletRequest request){
		Response response = new Response(new Date(), HttpStatus.CONFLICT.value(), HttpStatus.CONFLICT, e.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(response, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(UserPrincipalNotFoundException.class)
	public ResponseEntity<Response> userNotFoundException(UserNotFoundException e, HttpServletRequest request){
		Response response = new Response(new Date(), HttpStatus.NOT_FOUND.value(), HttpStatus.NOT_FOUND, e.getMessage(), request.getRequestURI());
		return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	}
}
