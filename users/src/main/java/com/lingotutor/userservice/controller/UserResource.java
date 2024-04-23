package com.lingotutor.userservice.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ROLE_USER')") 
public class UserResource {
	
	@GetMapping("/profile") 
	public String userProfile(@RequestHeader("username") String username, @RequestHeader("userId") String userId) { 
		return "Welcome to User Profile,"+username + "userId: "+userId; 
	} 
}
