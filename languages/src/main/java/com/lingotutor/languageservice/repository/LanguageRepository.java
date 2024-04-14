package com.lingotutor.languageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lingotutor.languageservice.entity.Language;

public interface LanguageRepository extends JpaRepository<Language, Long> {

}
