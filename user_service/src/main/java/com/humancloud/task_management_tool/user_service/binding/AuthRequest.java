package com.humancloud.task_management_tool.user_service.binding;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthRequest 
{
	String username;
	String password;
}
