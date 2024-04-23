package com.lingotutor.quizzes.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class MultiChoiceQuestion {
	@Id
	@GeneratedValue
	private long id;
	
	private String question;

	@OneToMany(mappedBy = "mcq")
	private List<Choice> choices;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	private Quiz quiz;

	protected MultiChoiceQuestion() {
	}

	public MultiChoiceQuestion(long id, String question, List<Choice> choices, Quiz quiz) {
		super();
		this.id = id;
		this.question = question;
		this.choices = choices;
		this.quiz = quiz;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public List<Choice> getChoices() {
		return choices;
	}

	public void setChoices(List<Choice> choices) {
		this.choices = choices;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	
}
