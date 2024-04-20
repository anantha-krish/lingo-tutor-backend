package com.lingotutor.languageservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lingotutor.languageservice.entity.Article;
import com.lingotutor.languageservice.entity.Language;
import com.lingotutor.languageservice.entity.Section;
import com.lingotutor.languageservice.repository.ArticleRepository;
import com.lingotutor.languageservice.repository.LanguageRepository;
import com.lingotutor.languageservice.repository.SectionRepository;

@RestController
@RequestMapping("/languages")
public class LanguageResource {

	@Autowired
	private LanguageRepository langRepo;
	
	@Autowired
	private SectionRepository sectionRepo;
	
	@Autowired
	private ArticleRepository articleRepo;
	
	@GetMapping("/user") 
	public String helloUser(@RequestHeader("username") String userName) {
		return "Hello "+ userName;
	}
	
	@GetMapping
	public  List<Language> getAllLanguages() {
		return langRepo.findAll();
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
