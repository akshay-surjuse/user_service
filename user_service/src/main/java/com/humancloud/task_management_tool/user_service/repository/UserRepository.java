package com.humancloud.task_management_tool.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humancloud.task_management_tool.user_service.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	public User findByUsername(String username);

	public User findByUsernameAndPassword(String username, String password);

}
