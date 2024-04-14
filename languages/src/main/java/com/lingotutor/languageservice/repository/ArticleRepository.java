package com.lingotutor.languageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lingotutor.languageservice.entity.Article;

public interface ArticleRepository extends JpaRepository<Article, Long> {

}
