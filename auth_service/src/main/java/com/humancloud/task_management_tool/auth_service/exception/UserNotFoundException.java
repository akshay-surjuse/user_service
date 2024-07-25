package com.humancloud.task_management_tool.auth_service.exception;

public class UserNotFoundException extends RuntimeException
{
	public UserNotFoundException(String message) {
		super(message);
	}
}
