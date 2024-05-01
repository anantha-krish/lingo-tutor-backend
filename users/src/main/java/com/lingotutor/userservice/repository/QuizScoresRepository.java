package com.lingotutor.userservice.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingotutor.userservice.entity.QuizScores;
import com.lingotutor.userservice.entity.UserInfo;
 

@Repository
public interface QuizScoresRepository extends JpaRepository<QuizScores, Long> { 
	
	Optional<QuizScores> findByQuizId(long quizId);
	Optional<List<QuizScores>> findAllByUserInfo(UserInfo userInfo);

}
