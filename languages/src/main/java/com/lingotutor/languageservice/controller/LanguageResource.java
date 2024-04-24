package com.lingotutor.languageservice.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.languageservice.bean.LanguageResponse;
import com.lingotutor.languageservice.bean.Quiz;
import com.lingotutor.languageservice.entity.Article;
import com.lingotutor.languageservice.entity.Language;
import com.lingotutor.languageservice.entity.Section;
import com.lingotutor.languageservice.proxy.QuizServiceProxy;
import com.lingotutor.languageservice.repository.ArticleRepository;
import com.lingotutor.languageservice.repository.LanguageRepository;
import com.lingotutor.languageservice.repository.SectionRepository;

import feign.Retryer;

@RestController
@RequestMapping("/languages")
public class LanguageResource {
	
	@Autowired
	private QuizServiceProxy quizProxy;

	@Autowired
	private LanguageRepository langRepo;
	
	@Autowired
	private SectionRepository sectionRepo;
	
	@Autowired
	private ArticleRepository articleRepo;
	
	@GetMapping("/user") 
	public String helloUser(@RequestHeader("username") String userName,@RequestHeader("userId") String userId) {
		return "Hello "+ userName + userId;
	}
	
	@GetMapping
	public  List<Language> getAllLanguages() {
		return langRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public  ResponseEntity<Object> getLanguageById(@PathVariable("id") Long id) {
		Optional<Language> language = langRepo.findById(id);
		if(language.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		List<Quiz>quizzes=quizProxy.getQuizzesByLanguageId(id);
		LanguageResponse response= new LanguageResponse(language.get(), quizzes);
		
		return ResponseEntity.ok(response);
		
	}
	
	@GetMapping("/sections")
	public  List<Section> getAllSections() {
		return sectionRepo.findAll();
	}
	@GetMapping("/articles")
	public  List<Article> getAllArticles() {
		return articleRepo.findAll();
	}
}
