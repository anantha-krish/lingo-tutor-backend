package com.lingotutor.userservice.bean;

public class UserQuizScoreRequest {
	
		private Long quizId;
		
		private double score;
		
		private int maxScore;

		protected UserQuizScoreRequest(){}
		
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
