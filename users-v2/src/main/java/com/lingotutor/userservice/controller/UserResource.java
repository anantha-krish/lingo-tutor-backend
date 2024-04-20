package com.lingotutor.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserResource {
	@GetMapping("/profile") 
	@PreAuthorize("hasAuthority('ROLE_USER')") 
	public String userProfile() { 
		return "Welcome to User Profile"; 
	} 
}
