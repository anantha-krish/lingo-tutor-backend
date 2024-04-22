package com.lingotutor.quizzes.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lingotutor.quizzes.entity.Quiz;


public interface QuizRepository extends JpaRepository<Quiz, Long> {
	
	Optional<List<QuizIdNameAndLevel>> findAllByLanguageId(Long id );

}
