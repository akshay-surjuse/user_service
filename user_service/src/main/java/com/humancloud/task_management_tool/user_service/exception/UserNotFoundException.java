package com.humancloud.task_management_tool.user_service.exception;

public class UserNotFoundException extends RuntimeException
{
	public UserNotFoundException(String message) {
		super(message);
	}
}
