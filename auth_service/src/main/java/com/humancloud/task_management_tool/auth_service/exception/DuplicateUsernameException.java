package com.humancloud.task_management_tool.auth_service.exception;

public class DuplicateUsernameException extends RuntimeException
{
	public DuplicateUsernameException(String message) 
	{
		super(message);
	}
}
