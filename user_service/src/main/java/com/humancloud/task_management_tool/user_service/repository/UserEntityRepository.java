package com.humancloud.task_management_tool.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humancloud.task_management_tool.user_service.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer>{
	
	public UserEntity findByUsername(String username);
}
