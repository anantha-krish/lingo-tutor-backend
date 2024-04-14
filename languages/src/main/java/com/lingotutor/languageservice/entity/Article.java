package com.lingotutor.languageservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Article {
	@Id
	private long Id;

	private String title;
	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	private String mediaLink;
	private String mediaType;
	private String shortDescription;
	private String description;
	
	@ManyToOne
	@JsonIgnore
	private Section section;
	
	protected Article() {}

	public Article(long id, String title, String mediaLink, String mediaType, String shortDescription,
			String description, Section section) {
		super();
		Id = id;
		this.title = title;
		this.mediaLink = mediaLink;
		this.mediaType = mediaType;
		this.shortDescription = shortDescription;
		this.description = description;
		this.section = section;
	}

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMediaLink() {
		return mediaLink;
	}

	public void setMediaLink(String mediaLink) {
		this.mediaLink = mediaLink;
	}

	public String getMediaType() {
		return mediaType;
	}

	public void setMediaType(String mediaType) {
		this.mediaType = mediaType;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Article [Id=" + Id + ", title=" + title + ", mediaLink=" + mediaLink + ", mediaType=" + mediaType
				+ ", shortDescription=" + shortDescription + ", description=" + description + "]";
	}
}
