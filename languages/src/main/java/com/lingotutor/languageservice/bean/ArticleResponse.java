package com.lingotutor.languageservice.bean;

import com.lingotutor.languageservice.entity.Article;

public class ArticleResponse extends Article {

	private long sectionId;

	protected ArticleResponse() {
	}

	public ArticleResponse(Article article) {
		super(article.getId(), article.getTitle(), article.getMediaLink(), article.getMediaType(),
				article.getShortDescription(), article.getDescription(), article.getSection());
		this.sectionId= article.getSection().getId();
	}

	public long getSectionId() {
		return sectionId;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

}
