package com.lingotutor.languageservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lingotutor.languageservice.bean.QuestionAnswer;
import com.lingotutor.languageservice.bean.Quiz;

@FeignClient(name = "quizzes")
public interface QuizServiceProxy {
	
	@GetMapping("/quizzes")
	public List<Quiz> getQuizzesByLanguageId(@RequestParam("languageId") Long languageId);
	
	@GetMapping("/quizzes/answers")
	public ResponseEntity<List<QuestionAnswer>> getAllAnswers(@RequestParam(name = "languageId",required = false) Long languageId,
			@RequestParam(name = "quizId",required = false) Long quizId);
}