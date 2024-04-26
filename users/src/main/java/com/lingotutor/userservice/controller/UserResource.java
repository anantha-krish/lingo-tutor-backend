package com.lingotutor.userservice.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.userservice.bean.ArticleVisitReponse;
import com.lingotutor.userservice.bean.UserQuizScoreRequest;
import com.lingotutor.userservice.entity.ArticleVisits;
import com.lingotutor.userservice.entity.QuizScores;
import com.lingotutor.userservice.entity.UserInfo;
import com.lingotutor.userservice.repository.ArticleVisitsRepository;
import com.lingotutor.userservice.repository.QuizScoresRepository;
import com.lingotutor.userservice.service.UserInfoService;

@RestController
@RequestMapping("/users")

public class UserResource {
	@Autowired
	ArticleVisitsRepository articleVisitsRepo;
	
	@Autowired
	QuizScoresRepository quizScoresRepo;

	@Autowired
	UserInfoService userInfoService;

	@GetMapping("/profile")
	@PreAuthorize("hasAuthority('ROLE_USER')") 
	public String userProfile(@RequestHeader("username") String username, @RequestHeader("userId") String userId) {
		return "Welcome to User Profile," + username + "userId: " + userId;
	}

	@GetMapping("/visits/articles")
	@PreAuthorize("hasAuthority('ROLE_USER')") 
	public ResponseEntity<Object> getAllVisitHistory(@RequestHeader("userId") Long userId) {
		var user = userInfoService.findUserById(userId);
		var list = articleVisitsRepo.findAllByUserInfoOrderByTimestampDesc(user);
		List<ArticleVisitReponse> responseList = list.get().stream().map(x -> new ArticleVisitReponse(x)).toList();
		return ResponseEntity.ok(responseList);
	}
	
	
	@GetMapping("/scores/languages")
	@PreAuthorize("hasAuthority('ROLE_USER')") 
	public ResponseEntity<Object> getAllLanguageScores(@RequestHeader("userId") Long userId) {
	 return ResponseEntity.ok(quizScoresRepo.findAll());
	}
	
	@PostMapping("/scores/languages")
	public ResponseEntity<Object> saveQuizScore(@RequestHeader("userId") Long userId, @RequestBody UserQuizScoreRequest request) {
		var user = userInfoService.findUserById(userId);
		QuizScores entity;
		var savedScore = quizScoresRepo.findByQuizId(request.getQuizId());
		if(savedScore.isPresent()) {
			entity =savedScore.get();
			entity.setScore(request.getScore());
			entity.setMaxScore(request.getMaxScore());
		}
		else {
			entity = new QuizScores(user,request.getQuizId(), request.getScore(), request.getMaxScore());
		}
		
	 return ResponseEntity.ok(quizScoresRepo.save(entity));
	}
	

	@PostMapping("/visits/articles/{articleId}")
	@PreAuthorize("hasAuthority('ROLE_USER')") 
	public ResponseEntity<Object> saveVisitHistory(@RequestHeader("userId") Long userId,
			@PathVariable("articleId") Long articleId) {
		UserInfo user = userInfoService.findUserById(userId);
		Optional<ArticleVisits> prevVisit =articleVisitsRepo.findByArticleId(articleId);
		ArticleVisits visitEntry =null;
		// means article was previously visited
		if(prevVisit.isPresent()) {
			visitEntry = prevVisit.get();
			//update timestamp
			visitEntry.setTimestamp(LocalDateTime.now());
		}
		else {
		visitEntry = new ArticleVisits(user, articleId);
		}
		var savedHistory = articleVisitsRepo.save(visitEntry);
		ArticleVisitReponse response = new ArticleVisitReponse(savedHistory);
		return ResponseEntity.ok(response);
	}

}
