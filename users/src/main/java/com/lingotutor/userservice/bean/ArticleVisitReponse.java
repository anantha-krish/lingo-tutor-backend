package com.lingotutor.userservice.bean;

import java.time.format.DateTimeFormatter;

import com.lingotutor.userservice.entity.ArticleVisits;

public class ArticleVisitReponse {

	private Long userId;
	private Long articleId;
	private String date;

	public ArticleVisitReponse(ArticleVisits visit) {
		super();
		this.userId = visit.getUserInfo().getId();
		this.articleId = visit.getArticleId();
		this.date = visit.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE);
	}

	public Long getUserId() {
		return userId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public String getDate() {
		return date;
	}

}
