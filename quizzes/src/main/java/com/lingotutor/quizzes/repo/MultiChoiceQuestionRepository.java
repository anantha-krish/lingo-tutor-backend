package com.lingotutor.quizzes.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lingotutor.quizzes.entity.MultiChoiceQuestion;


public interface MultiChoiceQuestionRepository extends JpaRepository<MultiChoiceQuestion, Long> {
	Optional<List<MultiChoiceQuestionId>>findAllByQuizId(long quizId);
}
