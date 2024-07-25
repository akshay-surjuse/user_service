package com.humancloud.task_management_tool.auth_service.binding;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AuthResponse {
	private String token;
	private String loginValid;
}
