package com.lingotutor.userservice.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.userservice.dto.ArticleVisitReponse;
import com.lingotutor.userservice.entity.ArticleVisits;
import com.lingotutor.userservice.entity.UserInfo;
import com.lingotutor.userservice.repository.ArticleVisitsRepository;
import com.lingotutor.userservice.service.UserInfoService;

@RestController
@RequestMapping("/users")
//@PreAuthorize("hasAuthority('ROLE_USER')") 
public class UserResource {
	@Autowired
	ArticleVisitsRepository articleVisitsRepo;

	@Autowired
	UserInfoService userInfoService;

	@GetMapping("/profile")
	public String userProfile(@RequestHeader("username") String username, @RequestHeader("userId") String userId) {
		return "Welcome to User Profile," + username + "userId: " + userId;
	}

	@GetMapping("/visits/articles")
	public ResponseEntity<Object> saveVisitHistory(@RequestHeader("userId") Long userId) {
		var user = userInfoService.findUserById(userId);
		var list = articleVisitsRepo.findAllByUserInfoOrderByTimestampDesc(user);
		List<ArticleVisitReponse> responseList = list.get().stream().map(x -> new ArticleVisitReponse(x)).toList();
		return ResponseEntity.ok(responseList);
	}

	@PostMapping("/visits/articles/{articleId}")
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
