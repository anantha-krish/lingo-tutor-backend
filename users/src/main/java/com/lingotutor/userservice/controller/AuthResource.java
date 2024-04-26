package com.lingotutor.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.userservice.bean.AuthRequest;
import com.lingotutor.userservice.bean.UserInfoResponse;
import com.lingotutor.userservice.entity.UserInfo;
import com.lingotutor.userservice.service.JwtService;
import com.lingotutor.userservice.service.UserInfoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthResource {

	@Autowired
	private UserInfoService service;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
   @PreAuthorize("hasAuthority('ROLE_ADMIN')") 
	public String welcome() {
		return "Welcome this endpoint is not secure";
	}

	@PostMapping("/register")
	public ResponseEntity<UserInfoResponse> addNewUser(@Valid @RequestBody UserInfo userInfo) {
		//plan HATEOAS example here
		return new ResponseEntity<UserInfoResponse>(service.addUser(userInfo),HttpStatus.CREATED);
	}

	@PostMapping("/token")
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
				authRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(token);
		if (authentication.isAuthenticated()) {
			var user = service.loadUserByUsername(authRequest.getUsername());
			return jwtService.generateToken(user.getUserId(),user.getUsername());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

	@GetMapping("/validate")
	public String validateToken(@RequestParam("token") String token) {
		service.validateToken(token);
		return "Token is valid";
	}
	

}
