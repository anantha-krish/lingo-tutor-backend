package com.lingotutor.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
	@GetMapping(path="/users",produces = "application/json") 
	public ResponseEntity<List<UserInfo>> getUsersList() { 
		return ResponseEntity.ok(service.getAllUsers());
	} 
	@GetMapping(path="/admins",produces = "application/json") 
	public ResponseEntity<List<UserInfo>> getAdminList() { 
		return ResponseEntity.ok( service.getAllAdmins());
	}

}
