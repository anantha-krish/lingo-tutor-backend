package com.lingotutor.userservice.bean;

import com.lingotutor.userservice.entity.UserInfo;

public class UserProfileResponse extends UserProfileRequest {

	private Long userId;

	private String userName;
	
	private String roles;

	public UserProfileResponse(UserInfo user) {
		super(user.getFirstName(), user.getLastName(), user.getEmail());
		this.userId = user.getId();
		this.userName = user.getUsername();
		this.roles = user.getRoles();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}
}
