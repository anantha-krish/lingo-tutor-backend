package com.lingotutor.languageservice.bean;

import com.lingotutor.languageservice.entity.Article;

public class ArticleResponse extends Article {
	
	private String port;
	
	private long sectionId;

	protected ArticleResponse() {
	}

	public ArticleResponse(Article article,String port) {
		super(article.getId(), article.getTitle(), article.getMediaLink(), article.getMediaType(),
				article.getShortDescription(), article.getDescription(), article.getSection());
		this.sectionId= article.getSection().getId();
		this.port=port;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public long getSectionId() {
		return sectionId;
	}

	public void setSectionId(long sectionId) {
		this.sectionId = sectionId;
	}

}
