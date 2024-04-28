package com.lingotutor.languageservice.bean;

import org.springframework.stereotype.Component;

@Component
public class Quiz extends Menu{
	
	private String level;
	
	protected Quiz(){}

	public Quiz(long id, String name, String level) {
		super(id,name);
		this.level = level;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
