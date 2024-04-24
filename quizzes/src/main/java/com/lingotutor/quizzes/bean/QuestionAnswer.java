package com.lingotutor.quizzes.bean;

public class QuestionAnswer {
	private long mcq;
	private long choice;
	protected QuestionAnswer() {};
	public QuestionAnswer(long mcq, long choice) {
		super();
		this.mcq = mcq;
		this.choice = choice;
	}
	public long getMcq() {
		return mcq;
	}
	public void setMcq(long mcq) {
		this.mcq = mcq;
	}
	public long getChoice() {
		return choice;
	}
	public void setChoice(long choice) {
		this.choice = choice;
	}
	
}
