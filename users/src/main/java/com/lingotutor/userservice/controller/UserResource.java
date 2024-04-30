package com.lingotutor.userservice.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.userservice.bean.ArticleVisitReponse;
import com.lingotutor.userservice.bean.UserProfileRequest;
import com.lingotutor.userservice.bean.UserProfileResponse;
import com.lingotutor.userservice.bean.UserQuizScoreRequest;
import com.lingotutor.userservice.entity.ArticleVisits;
import com.lingotutor.userservice.entity.QuizScores;
import com.lingotutor.userservice.entity.UserInfo;
import com.lingotutor.userservice.repository.ArticleVisitsRepository;
import com.lingotutor.userservice.repository.QuizScoresRepository;
import com.lingotutor.userservice.service.UserInfoService;

@RestController
@RequestMapping("/user")

public class UserResource {
	@Autowired
	ArticleVisitsRepository articleVisitsRepo;

	@Autowired
	QuizScoresRepository quizScoresRepo;

	@Autowired
	UserInfoService userInfoService;

	@GetMapping("/profile")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<UserProfileResponse> getUserProfile(@RequestHeader("username") String username,
			@RequestHeader("userId") Long userId) {

		var user = userInfoService.findUserById(userId);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		UserProfileResponse response = new UserProfileResponse(user);
		return ResponseEntity.ok(response);
	}
	
	@PutMapping("/profile")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<UserProfileResponse> updateUserProfile(@RequestHeader("userId") Long userId,
			@RequestBody UserProfileRequest userProfile) {

		var user = userInfoService.findUserById(userId);
		if (user == null) {
			throw new UsernameNotFoundException("User not found");
		}
		String firstName = userProfile.getFirstName();
		String lastName = userProfile.getLastName();
		String email = userProfile.getEmail();
		if (!firstName.isBlank()) {
			user.setFirstName(firstName);
		}
		if (!lastName.isBlank()) {
			user.setLastName(lastName);
		}
		if (!email.isBlank()) {
			user.setEmail(email);
		}
		// plan HATEOAS example here
		return new ResponseEntity<UserProfileResponse>(userInfoService.addUser(user), HttpStatus.OK);
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
	
	@GetMapping("/scores/quizzes/{quizId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Object> getQuizScores(@RequestHeader("userId") Long userId,@PathVariable("quizId") Long quizId) {
		var attemptedQuiz = quizScoresRepo.findByQuizId(quizId);
		if(attemptedQuiz.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(attemptedQuiz.get());
	}

	@PostMapping("/scores/quizzes")
	public ResponseEntity<Object> saveQuizScore(@RequestHeader("userId") Long userId,
			@RequestBody UserQuizScoreRequest request) {
		var user = userInfoService.findUserById(userId);
		QuizScores entity;
		var savedScore = quizScoresRepo.findByQuizId(request.getQuizId());
		if (savedScore.isPresent()) {
			entity = savedScore.get();
			entity.setScore(request.getScore());
			entity.setMaxScore(request.getMaxScore());
		} else {
			entity = new QuizScores(user, request.getQuizId(), request.getScore(), request.getMaxScore());
		}

		return ResponseEntity.ok(quizScoresRepo.save(entity));
	}

	@PostMapping("/visits/articles/{articleId}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<Object> saveVisitHistory(@RequestHeader("userId") Long userId,
			@PathVariable("articleId") Long articleId) {
		UserInfo user = userInfoService.findUserById(userId);
		Optional<ArticleVisits> prevVisit = articleVisitsRepo.findByArticleId(articleId);
		ArticleVisits visitEntry = null;
		// means article was previously visited
		if (prevVisit.isPresent()) {
			visitEntry = prevVisit.get();
			// update timestamp
			visitEntry.setTimestamp(LocalDateTime.now());
		} else {
			visitEntry = new ArticleVisits(user, articleId);
		}
		var savedHistory = articleVisitsRepo.save(visitEntry);
		ArticleVisitReponse response = new ArticleVisitReponse(savedHistory);
		return ResponseEntity.ok(response);
	}

}
