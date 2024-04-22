package com.lingotutor.quizzes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.quizzes.entity.AnswerKey;
import com.lingotutor.quizzes.entity.Quiz;
import com.lingotutor.quizzes.repo.QuizIdNameAndLevel;
import com.lingotutor.quizzes.repo.QuizRepository;

import jakarta.ws.rs.QueryParam;

@RestController
@RequestMapping("/quizzes")
public class QuizResource {

	@Autowired
	private QuizRepository quizRepo;

	@GetMapping("/all")
	public ResponseEntity<Object> getAllQuizzes() {
		return ResponseEntity.ok(quizRepo.findAll());
	}

	@GetMapping
	public ResponseEntity<Object> getQuizzesByLanguageId(
			@RequestParam(name = "languageId", required = false) Long languageId) {
		if (languageId == null) {
			return getAllQuizzes();
		}

		return ResponseEntity.ok(quizRepo.findAllByLanguageId(languageId).get());
	}

	@GetMapping("/answers")
	public List<AnswerKey> getAllAnswers() {
		return quizRepo.findById(1001L).get().getAnswers();
	}
}
