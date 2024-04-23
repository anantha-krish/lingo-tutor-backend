package com.lingotutor.quizzes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

@Entity
public class AnswerKey {
	@Id
	@JsonIgnore
	private int id;
	
	@ManyToOne
	@JsonIgnore
	private Quiz quiz;
	
	@OneToOne
	private MultiChoiceQuestion mcq;
	
	@OneToOne
	private Choice choice;

	protected AnswerKey() {
	}

	public AnswerKey(int id, MultiChoiceQuestion mcq, Choice choice) {
		super();
		this.id = id;
		this.mcq = mcq;
		this.choice = choice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public long getMcq() {
		return mcq.getId();
	}

	public void setMcq(MultiChoiceQuestion mcq) {
		this.mcq = mcq;
	}

	public long getChoice() {
		return choice.getId();
	}

	public void setChoice(Choice choice) {
		this.choice = choice;
	}

	
}
