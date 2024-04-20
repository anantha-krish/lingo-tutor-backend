package com.lingotutor.userservice.service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

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

		Optional<UserInfo> userDetail = userRepo.findByName(username); 

		// Converting userDetail to UserDetails 
		return userDetail.map(UserInfoDetails::new) 
				.orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
	} 

	public String addUser(UserInfo userInfo) { 
		userInfo.setPassword(encoder.encode(userInfo.getPassword())); 
		userRepo.save(userInfo); 
		return "User Added Successfully"; 
	} 

    public String generateToken(String username) {
        return jwtService.generateToken(username);
    }

    public void validateToken(String token) {
        jwtService.validateToken(token);
    }

} 
