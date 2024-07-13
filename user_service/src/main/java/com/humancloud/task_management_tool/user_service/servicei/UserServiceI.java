package com.humancloud.task_management_tool.user_service.servicei;


import com.humancloud.task_management_tool.user_service.entity.Role;
import com.humancloud.task_management_tool.user_service.entity.User;

public interface UserServiceI 
{
	public User addUser(User user);
	
	public User loginCheck(String username, String password);
	
	public User getUserById(Integer userId);
	
	public User updateUserById(Integer userId, User user);
	
	public Role assignRoleToUser(Integer userId, Role role);
}
