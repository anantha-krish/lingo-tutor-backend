package com.lingotutor.userservice.bean;

public class ArticleIdRequest {

	private Long articleId;
	
	protected ArticleIdRequest() {}
	public ArticleIdRequest(Long articleId) {
		super();
		this.articleId = articleId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}
	
}
