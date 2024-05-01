package com.lingotutor.userservice.bean;

import java.util.List;

import com.lingotutor.userservice.entity.ArticleVisits;

public class UserArticleVisitReponse {

	private Long userId;
	private List<ArticleVisits> history;
	
	public UserArticleVisitReponse( Long userId, List<ArticleVisits> history) {
		super();
		this.userId = userId;
		this.history = history;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public List<ArticleVisits> getHistory() {
		return history;
	}
	public void setHistory(List<ArticleVisits> history) {
		this.history = history;
	} 



}
