package com.lingotutor.languageservice.bean;

import com.lingotutor.languageservice.entity.Language;

public class LanguageIdAndName {
	private Long id;

	private String name;
	

	public LanguageIdAndName(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	

	public LanguageIdAndName(Language lang) {
		super();
		this.id = lang.getId();
		this.name = lang.getName();
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
	
	
}
