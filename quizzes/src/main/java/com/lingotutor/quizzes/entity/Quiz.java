package com.lingotutor.quizzes.entity;

import java.util.List;

import jakarta.annotation.Nullable;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Quiz {
	@Id
	private long id;
	
	private String name;
	
	private String level;
	
	@OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY)
	private List<MultiChoiceQuestion> mcqs;
	
	@OneToMany(mappedBy = "quiz",fetch = FetchType.LAZY )
	private List<AnswerKey> answers;
	
	@Nullable
	private long languageId;
	
	public List<AnswerKey> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerKey> answers) {
		this.answers = answers;
	}
	protected Quiz() {}



	public Quiz(long id, String name, String level, List<MultiChoiceQuestion> mcqs, List<AnswerKey> answers,
			long languageId) {
		super();
		this.id = id;
		this.name = name;
		this.level = level;
		this.mcqs = mcqs;
		this.answers = answers;
		this.languageId = languageId;
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
	
	public long getLanguageId() {
		return languageId;
	}
	public void setLanguageId(long languageId) {
		this.languageId = languageId;
	}
	

}
