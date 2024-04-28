package com.lingotutor.languageservice.bean;

import org.springframework.stereotype.Component;

@Component
public class Menu {
	private long id;	
	private String name;
	
	public Menu(long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	protected Menu() {}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
