package com.lingotutor.quizzes.bean;

public class UserQuizScoreRequest {
	
	private Long userId;

	private Long quizId;

	private double score;

	private int maxScore;

	protected UserQuizScoreRequest() {
	}

	public UserQuizScoreRequest(Long userId,Long quizId, double score, int maxScore) {
		super();
		this.userId = userId;
		this.quizId = quizId;
		this.score = score;
		this.maxScore = maxScore;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getQuizId() {
		return quizId;
	}

	public void setQuizId(Long quizId) {
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
