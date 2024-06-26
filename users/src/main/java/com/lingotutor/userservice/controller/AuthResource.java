package com.lingotutor.userservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.lingotutor.userservice.bean.LoginResponse;
import com.lingotutor.userservice.bean.MessageResponse;
import com.lingotutor.userservice.bean.UserProfileResponse;
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


	@PostMapping(path="/register",consumes = "application/json",produces = "application/json")
	public ResponseEntity<UserProfileResponse> addNewUser(@Valid @RequestBody UserInfo userInfo) {
		return new ResponseEntity<UserProfileResponse>(service.addUser(userInfo),HttpStatus.CREATED);
	}

	@PostMapping(path="/login",consumes = "application/json",produces = "application/json")
	public Object authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
				authRequest.getPassword());
		Authentication authentication = authenticationManager.authenticate(token);
		if (authentication.isAuthenticated()) {
			var user = service.loadUserByUsername(authRequest.getUsername());
			var resp = new LoginResponse (jwtService.generateToken(user.getUserId(),user.getUsername()),user.getAuthorities());
			return ResponseEntity.ok(resp);
					
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}
	}

	@GetMapping(path="/validate",produces = "application/json")
	public ResponseEntity<MessageResponse> validateToken(@RequestParam("token") String token) {
		service.validateToken(token);
		return ResponseEntity.ok(new MessageResponse("Your token is valid"));
	}
	

}
