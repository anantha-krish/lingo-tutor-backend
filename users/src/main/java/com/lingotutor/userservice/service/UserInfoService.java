package com.lingotutor.userservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.lingotutor.userservice.config.UserInfoDetails;
import com.lingotutor.userservice.entity.UserInfo;
import com.lingotutor.userservice.repository.UserInfoRepository;

@Service
public class UserInfoService implements UserDetailsService {

	@Autowired
	private UserInfoRepository userRepo;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtService jwtService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<UserInfo> userDetail = userRepo.findByUsername(username);

		// Converting userDetail to UserDetails
		return userDetail.map(UserInfoDetails::new)
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username));
	}

	public String addUser(UserInfo userInfo) {

		userInfo.setPassword(encoder.encode(userInfo.getPassword()));
		userRepo.save(userInfo);

		return "User Added Successfully";
	}

	public List<UserInfo> getAllUsers() {
		
		var users = userRepo.findAllByRolesContaining("ROLE_USER");
		
		if(users.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No users");
		}
		return users.get();
	}

	public List<UserInfo> getAllAdmins() {
		
		var users = userRepo.findAllByRolesContaining("ROLE_ADMIN");
		
		if(users.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Admins");
		}
		return users.get();
	}
	
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}

	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
