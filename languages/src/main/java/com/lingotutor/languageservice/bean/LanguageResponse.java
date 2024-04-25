package com.lingotutor.languageservice.bean;

import java.util.List;

import com.lingotutor.languageservice.entity.Language;
import com.lingotutor.languageservice.entity.Section;

public class LanguageResponse {

	private Long id;

	private String name;


	private List<Section> sections;
	
	private List<Quiz> quizzes;
	
	public LanguageResponse() {}
	public LanguageResponse(Language language, List<Quiz> quizzes) {
		super();
		this.id = language.getId();
		this.name = language.getName();
		this.sections = language.getSections();
		this.quizzes = quizzes;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Section> getSections() {
		return sections;
	}

	public void setSections(List<Section> sections) {
		this.sections = sections;
	}

	public List<Quiz> getQuizzes() {
		return quizzes;
	}

	public void setQuizzes(List<Quiz> quizzes) {
		this.quizzes = quizzes;
	}
}