package com.lingotutor.languageservice;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/languages")
public class LanguageResource {

	@GetMapping
	public  Object getAllLanguages() {
		return new  Language(100,"hello");
	}
}
