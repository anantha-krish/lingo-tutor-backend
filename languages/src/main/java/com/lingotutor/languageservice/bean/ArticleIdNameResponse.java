package com.lingotutor.languageservice.bean;

import com.lingotutor.languageservice.entity.Article;

public class ArticleIdNameResponse {
	
	private IdAndName article;
	private IdAndName section;
	private IdAndName language;
	
	public ArticleIdNameResponse(IdAndName article, IdAndName section, IdAndName language) {
		super();
		this.article = article;
		this.section = section;
		this.language = language;
	}
	public ArticleIdNameResponse(Article article) {
		super();
		this.article = new IdAndName(article.getId(),article.getTitle());
		this.section = new IdAndName(article.getSection().getId(),article.getSection().getName());
		this.language = new IdAndName(article.getSection().getLanguage().getId(),article.getSection().getLanguage().getName());;
	}
	public IdAndName getArticle() {
		return article;
	}
	public IdAndName getSection() {
		return section;
	}
	public IdAndName getLanguage() {
		return language;
	}
}
