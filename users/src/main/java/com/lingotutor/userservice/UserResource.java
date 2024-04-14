package com.lingotutor.userservice;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserResource {

    @GetMapping
	public String getAllUsers() {
		return "hello";
	}
    @GetMapping("/{userId}")
	public String getByUserId(@PathVariable("userId") String userId) {
		return "hello -> User"+ userId;
	}
}
