package com.lingotutor.quizzes.bean;

import com.lingotutor.quizzes.repo.QuizIdNameLevelAndAnswers;

public class QuizNameAndLevelBean {
	private long id;
	
	private String name;
	
	private String level;
	
	

	public QuizNameAndLevelBean(QuizIdNameLevelAndAnswers obj) {
		super();
		this.id = obj.getId();
		this.name = obj.getName();
		this.level = obj.getLevel();
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
