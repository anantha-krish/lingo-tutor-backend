package com.lingotutor.quizzes.entity;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Quiz {
	@Id
	private long id;
	
	private String name;
	
	private String level;
	
	@OneToMany(mappedBy = "quiz")
	private List<MultiChoiceQuestion> mcqs;
	
	@OneToMany(mappedBy = "quiz")
	private List<AnswerKey> answers;
	
	public List<AnswerKey> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerKey> answers) {
		this.answers = answers;
	}
	protected Quiz() {}


	public Quiz(long id, String name, String level, List<MultiChoiceQuestion> mcqs, List<AnswerKey> answers) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.mcqs = mcqs;
		this.answers = answers;
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

	public List<MultiChoiceQuestion> getMcqs() {
		return mcqs;
	}

	public void setMcqs(List<MultiChoiceQuestion> mcqs) {
		this.mcqs = mcqs;
	}
	

}
