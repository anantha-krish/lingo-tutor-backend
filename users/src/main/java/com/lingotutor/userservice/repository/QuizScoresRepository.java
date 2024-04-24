package com.lingotutor.userservice.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingotutor.userservice.entity.QuizScores; 

@Repository
public interface QuizScoresRepository extends JpaRepository<QuizScores, Long> { 

}
