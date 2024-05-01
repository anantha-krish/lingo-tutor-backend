package com.lingotutor.quizzes.repo;

import java.util.List;

import com.lingotutor.quizzes.entity.AnswerKey;

public interface QuizIdNameLevelAndAnswers {
	long getId();

	String getName();

	String getLevel();
	
	List<AnswerKey> getAnswers();
}
