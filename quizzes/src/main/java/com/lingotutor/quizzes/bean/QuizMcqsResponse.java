package com.lingotutor.quizzes.bean;

import java.util.List;

public class QuizMcqsResponse {
	
	List<Long> mcqs;

	public List<Long> getMcqs() {
		return mcqs;
	}

	public void setMcqs(List<Long> mcqs) {
		this.mcqs = mcqs;
	}

	public QuizMcqsResponse() {
		super();
	}

	public QuizMcqsResponse(List<Long> mcqs) {
		super();
		this.mcqs = mcqs;
	}
	

}
