package com.lingotutor.userservice.dto;

import java.time.LocalDateTime;

import com.lingotutor.userservice.entity.ArticleVisits;

public class ArticleVisitReponse {
	
	private Long userId;
	
	private Long articleId;
	
	private LocalDateTime timestamp;
	

	public ArticleVisitReponse(ArticleVisits visit) {
		super();
		this.userId = visit.getUserInfo().getId();
		this.articleId = visit.getArticleId();
		this.timestamp = visit.getTimestamp();
	}

	public long getUserInfo() {
		return userId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
	
	
}
