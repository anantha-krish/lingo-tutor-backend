package com.lingotutor.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.userservice.entity.UserInfo;
import com.lingotutor.userservice.service.UserInfoService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("hasAuthority('ROLE_ADMIN')") 
public class AdminResource {
	@Autowired
	private UserInfoService service;
	/* Only Admin should add contents*/
	@GetMapping("/users") 
	public List<UserInfo> getUsersList() { 
		return service.getAllUsers();
	} 
	@GetMapping("/admins") 
	public List<UserInfo> getAdminList() { 
		return service.getAllAdmins();
	}

}
