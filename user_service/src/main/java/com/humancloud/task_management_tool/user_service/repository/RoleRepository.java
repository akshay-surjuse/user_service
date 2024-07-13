package com.humancloud.task_management_tool.user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.humancloud.task_management_tool.user_service.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer>{

}
