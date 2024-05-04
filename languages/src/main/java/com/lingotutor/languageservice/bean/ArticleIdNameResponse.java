package com.lingotutor.languageservice.bean;

import com.lingotutor.languageservice.entity.Article;

public class ArticleIdNameResponse {
	
	private IdAndName article;
	private IdAndName section;
	private IdAndName language;
	private boolean isActive;
	
	public ArticleIdNameResponse(Long articleId) {
		super();
		this.article = new IdAndName(articleId, "");
		this.section = null;
		this.language = null;
		setActive(false);
	}
	public ArticleIdNameResponse(Article article) {
		super();
		this.article = new IdAndName(article.getId(),article.getTitle());
		this.section = new IdAndName(article.getSection().getId(),article.getSection().getName());
		this.language = new IdAndName(article.getSection().getLanguage().getId(),article.getSection().getLanguage().getName());;
		setActive(true);
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
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
}
