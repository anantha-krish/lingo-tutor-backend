package com.lingotutor.languageservice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;

@Entity
public class Section {

	@Id
	private long id;
	private String name;
	
	@OneToMany(mappedBy = "section")
	@OrderBy("id")
	private List<Article> articles;
	
	@ManyToOne
	@JsonIgnore
	private Language language;
	
	protected Section() {}
	
	public Section(long id, String name, List<Article> articles, Language language) {
		super();
		this.id = id;
		this.name = name;
		this.articles = articles;
		this.language = language;
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

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}



}
