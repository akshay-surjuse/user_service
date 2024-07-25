package com.humancloud.task_management_tool.user_service.servicei;

import java.util.List;

import com.humancloud.task_management_tool.user_service.entity.Role;
import com.humancloud.task_management_tool.user_service.entity.UserEntity;

public interface UserEntityServiceI {
	
	public UserEntity getUserById(Integer userId);
	
	public UserEntity updateUserById(Integer userId, UserEntity userEntity);
	
	public String assignRoleToUser(Integer userId, List<Role> roles);
}
