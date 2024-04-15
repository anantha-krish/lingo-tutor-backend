package com.lingotutor.quizzes.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Choice {

	@Id
	private long id;
	
	@Column(name="choice_label")
	private String label;
	
	@Column(name="choice_value")
	private String value;
	
	@ManyToOne
	@JsonIgnore
	private MultiChoiceQuestion mcq;
	
	protected Choice() {}
	public Choice(long id, String label, String value, MultiChoiceQuestion mcq) {
		super();
		this.id = id;
		this.label = label;
		this.value = value;
		this.mcq = mcq;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public MultiChoiceQuestion getMcq() {
		return mcq;
	}

	public void setMcq(MultiChoiceQuestion mcq) {
		this.mcq = mcq;
	}


	
}
