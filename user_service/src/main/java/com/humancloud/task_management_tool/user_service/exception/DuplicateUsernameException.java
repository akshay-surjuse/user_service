package com.humancloud.task_management_tool.user_service.exception;

public class DuplicateUsernameException extends RuntimeException
{
	public DuplicateUsernameException(String message) 
	{
		super(message);
	}
}
