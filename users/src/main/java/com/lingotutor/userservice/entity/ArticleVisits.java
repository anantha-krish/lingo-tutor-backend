package com.lingotutor.userservice.entity;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class ArticleVisits {
	@Id
	@GeneratedValue
	@JsonIgnore
	private long id;
	
	@ManyToOne
	@JsonIgnore
	private UserInfo userInfo;
	
	@Column(unique = true)
	private Long articleId;
	
	private LocalDateTime timestamp;
	
	public ArticleVisits() {};
	
	public ArticleVisits(UserInfo userInfo, Long articleId) {
		super();
		this.userInfo = userInfo;
		this.articleId = articleId;
		this.timestamp = LocalDateTime.now();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public UserInfo getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfo userInfo) {
		this.userInfo = userInfo;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}
	
	
}
