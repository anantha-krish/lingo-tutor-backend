package com.lingotutor.userservice.config;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.lingotutor.userservice.entity.UserInfo; 

public class UserInfoDetails implements UserDetails { 
	
	private static final long serialVersionUID = -3623932283680927252L;
	private String username; 
	private String password; 
	private long userId;
	private List<GrantedAuthority> authorities; 

	public UserInfoDetails(UserInfo userInfo) { 
		username = userInfo.getUsername(); 
		password = userInfo.getPassword(); 
		userId= userInfo.getId();
		authorities = Arrays.stream(userInfo.getRoles().split(",")) 
				.map(SimpleGrantedAuthority::new) 
				.collect(Collectors.toList()); 
	} 

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() { 
		return authorities; 
	} 

	@Override
	public String getPassword() { 
		return password; 
	} 

	@Override
	public String getUsername() { 
		return username; 
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

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	} 
} 
