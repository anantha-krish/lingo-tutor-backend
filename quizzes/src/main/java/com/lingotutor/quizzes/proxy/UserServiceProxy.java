package com.lingotutor.quizzes.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.lingotutor.quizzes.bean.UserQuizScoreRequest;

@FeignClient(name = "users")
public interface UserServiceProxy {
	
	@PostMapping("/users/scores/languages")
	public ResponseEntity<Object> saveQuizScore(@RequestHeader("userId") Long userId, @RequestBody UserQuizScoreRequest request);
}