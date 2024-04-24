package com.lingotutor.quizzes.bean;

import java.util.List;

public class QuizSubmitRequest {
private List<QuestionAnswer> submittedAnswers;


private QuizSubmitRequest() {};


public List<QuestionAnswer> getSubmittedAnswers() {
	return submittedAnswers;
}

public void setSubmittedAnswers(List<QuestionAnswer> submittedAnswers) {
	this.submittedAnswers = submittedAnswers;
}


}
