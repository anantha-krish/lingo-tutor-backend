package com.lingotutor.userservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class QuizScores {

	@Id
	@GeneratedValue
	private long id;
	
	@ManyToOne
	@JsonIgnore
	private UserInfo userInfo;
	
	@Column(unique = true)
	private Long quizId;
	
	private double score;
	
	private int maxScore;
	
	public QuizScores() {}
	
	public QuizScores(UserInfo userInfo, long quizId, double score, int maxScore) {
		super();
		this.userInfo=userInfo;
		this.quizId = quizId;
		this.score = score;
		this.maxScore = maxScore;
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

	public void setQuizId(Long quizId) {
		this.quizId = quizId;
	}

	public long getQuizId() {
		return quizId;
	}

	public void setQuizId(long quizId) {
		this.quizId = quizId;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}
	
	
}
