package com.lingotutor.languageservice.bean;

import org.springframework.stereotype.Component;

@Component
public class Quiz{
	private long id;
	
	private String name;
	
	private String level;
	
	protected Quiz(){}

	public Quiz(long id, String name, String level) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
