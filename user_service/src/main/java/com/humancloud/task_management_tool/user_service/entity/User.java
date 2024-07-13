package com.humancloud.task_management_tool.user_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "user_details")
public class User
{
	@Id
	@SequenceGenerator(sequenceName = "mySequence", name = "mySequence", initialValue = 101, allocationSize = 1)
	@GeneratedValue(generator = "mySequence")
	private Integer userId;
	private String name;
	private String email;
	private String username;
	private String password;
}
