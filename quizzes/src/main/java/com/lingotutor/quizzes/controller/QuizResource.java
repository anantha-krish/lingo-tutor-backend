package com.lingotutor.quizzes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.quizzes.entity.AnswerKey;
import com.lingotutor.quizzes.entity.Quiz;
import com.lingotutor.quizzes.repo.QuizRepository;

@RestController
@RequestMapping("/quizzes")
public class QuizResource {
	
	@Autowired
	private QuizRepository quizRepo;
	
	@GetMapping
	public List<Quiz> getAll(){
		return quizRepo.findAll();
	}
	
	@GetMapping("/answers")
	public List<AnswerKey> getAllAnswers(){
		return quizRepo.findById(1001L).get().getAnswers();
	}
}
