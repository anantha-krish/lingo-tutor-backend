package com.lingotutor.languageservice.bean;

import java.util.List;

import org.springframework.stereotype.Component;

import com.lingotutor.languageservice.entity.Section;

@Component
public class SectionMenu extends Menu {

	private List<Menu> articles;
	public SectionMenu() {}

	public SectionMenu(Section section) {
		super(section.getId(),section.getName());
		this.articles = section.getArticles().stream().map(article -> new Menu(article.getId(), article.getTitle()))
				.toList();
	}

	public List<Menu> getArticles() {
		return articles;
	}

}
