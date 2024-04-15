package com.lingotutor.quizzes.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lingotutor.quizzes.entity.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

}
