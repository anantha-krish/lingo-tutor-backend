package com.lingotutor.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('ROLE_USER')") 
public class UserResource {
	
	@GetMapping("/profile") 
	public String userProfile() { 
		return "Welcome to User Profile"; 
	} 
}
