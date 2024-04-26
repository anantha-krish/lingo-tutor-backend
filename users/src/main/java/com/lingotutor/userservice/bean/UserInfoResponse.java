package com.lingotutor.userservice.bean;

import com.lingotutor.userservice.entity.UserInfo;

public class UserInfoResponse {
	private String fullName;

	private String username;

	private String email;

	private String roles;

	public UserInfoResponse(UserInfo userInfo) {
		super();
		this.fullName = userInfo.getFirstName().concat(" ").concat(userInfo.getLastName());
		this.username = userInfo.getUsername();
		this.email = userInfo.getEmail();
		this.roles = userInfo.getRoles();
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
