package com.lingotutor.userservice.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lingotutor.userservice.entity.ArticleVisits;
import com.lingotutor.userservice.entity.UserInfo; 

@Repository
public interface ArticleVisitsRepository extends JpaRepository<ArticleVisits, Long> { 
	Optional<List<ArticleVisits>> findAllByUserInfoOrderByTimestampDesc(UserInfo userInfo);
	Optional<ArticleVisits> findByArticleId(long articleId);
}
