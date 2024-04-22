package com.lingotutor.languageservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.lingotutor.languageservice.bean.Quiz;

@FeignClient(name = "quizzes")
public interface QuizServiceProxy {
	
	@GetMapping("/quizzes")
	public List<Quiz> getQuizzesByLanguageId(@RequestParam("languageId") Long languageId);
}