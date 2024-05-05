package com.lingotutor.userservice.bean;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class LoginResponse {
	
	private String token;
	
	private List<String> roles;
	
	public LoginResponse(String token, Collection<? extends GrantedAuthority> collection) {
		super();
		this.token = token;
		this.roles = collection.stream().map(x->x.getAuthority()).toList();
	}
	

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}


	public List<String> getRoles() {
		return roles;
	}


	public void setRoles(List<String> roles) {
		this.roles = roles;
	}




}
