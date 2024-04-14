package com.lingotutor.languageservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lingotutor.languageservice.entity.Section;

public interface SectionRepository extends JpaRepository<Section, Long> {

}
