package com.humancloud.task_management_tool.user_service.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.humancloud.task_management_tool.user_service.entity.Role;
import com.humancloud.task_management_tool.user_service.entity.User;
import com.humancloud.task_management_tool.user_service.exception.DuplicateUsernameException;
import com.humancloud.task_management_tool.user_service.exception.UserNotFoundException;
import com.humancloud.task_management_tool.user_service.repository.RoleRepository;
import com.humancloud.task_management_tool.user_service.repository.UserRepository;
import com.humancloud.task_management_tool.user_service.servicei.UserServiceI;

@Service
public class UserServiceImpl implements UserServiceI
{
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public User addUser(User user) {
		User u = repository.findByUsername(user.getUsername());
		if(u==null)
			return repository.save(user);
		else
			throw new DuplicateUsernameException("Duplicate username!" + "\n" + "User with entered username id already present");
	}

	@Override
	public User loginCheck(String username, String password) {
		User user = repository.findByUsernameAndPassword(username, password);
		if(user!=null)
			return user;
		else
			throw new UserNotFoundException("Invalid username or password");
	}

	@Override
	public User getUserById(Integer userId) {
		Optional<User> optional = repository.findById(userId);
		if(optional.isPresent())
			return optional.get();
		else 
			throw new UserNotFoundException("User with entered User Id is not present in database");
	}

	@Override
	public Role assignRoleToUser(Integer userId, Role role) {
		role.setUserId(userId);
		return roleRepository.save(role);
	}

	@Override
	public User updateUserById(Integer userId, User user) 
	{
		Optional<User> optional = repository.findById(userId);
		if(optional.isPresent()) {
			return repository.save(user);
		}else {
			throw new UserNotFoundException("User with entered userId is present in databse");
		}
	}

}
