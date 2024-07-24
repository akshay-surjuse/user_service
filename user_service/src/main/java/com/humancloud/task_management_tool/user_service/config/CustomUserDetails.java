package com.humancloud.task_management_tool.user_service.config;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.humancloud.task_management_tool.user_service.entity.UserEntity;


public class CustomUserDetails implements UserDetails{

	private String username;
	private String password;
	
	public CustomUserDetails(UserEntity userEntity) {
		this.username = userEntity.getUsername();
		this.password = userEntity.getPassword();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	@Override
	public String getPassword() {
		return username;
	}

	@Override
	public String getUsername() {
		return password;
	}
	
	@Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}