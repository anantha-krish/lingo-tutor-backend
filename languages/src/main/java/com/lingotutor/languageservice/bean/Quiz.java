package com.lingotutor.languageservice.bean;

import org.springframework.stereotype.Component;

@Component
public class Quiz extends Menu{
	
	private String level;
	
	private int maxScore;
	
	protected Quiz(){}

	public Quiz(long id, String name, String level,int maxScore) {
		super(id,name);
		this.level = level;
		this.maxScore=maxScore;
	}

	
	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
}
