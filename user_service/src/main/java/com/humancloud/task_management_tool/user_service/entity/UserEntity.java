package com.humancloud.task_management_tool.user_service.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class UserEntity 
{
	@Id
	@SequenceGenerator(sequenceName = "mySequence", name = "mySequence", initialValue = 101, allocationSize = 1)
	@GeneratedValue(generator = "mySequence")
	private Integer userId;
	private String name;
	private String email;
	private String username;
	private String password;
	@OneToMany(cascade = CascadeType.ALL)
	private List<Role> role;
}
