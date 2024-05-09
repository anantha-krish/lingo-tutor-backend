package com.lingotutor.userservice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class UserInfo {

	@Id
	@GeneratedValue
	private Long id;
	@NotBlank(message = "Please enter a value")
	@Size(min = 3, max = 20, message = "Should contain min 3 chars & max 20 chars")
	private String firstName;

	@Size(min = 3, max = 20, message = "Should contain min 3 chars & max 20 chars")
	private String lastName;

	@NotBlank(message = "Please enter a value")
	@Size(min = 3, max = 20, message = "Should contain min 3 chars & max 20 chars")
	@Column(unique = true)
	private String username;

	@NotBlank(message = "Please enter an email")
	@Column(unique = true)
	@Email(message = "Please enter a valid email")
	private String email;

	@NotBlank(message = "Please enter a password")
	private String password;

	@NotBlank(message = "Please speicfy a role")
	private String roles;

	@OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY)
	private List<QuizScores> quizScores;
	
	@OneToMany(mappedBy = "userInfo", fetch = FetchType.LAZY)
	private List<ArticleVisits> articleVisits;

	public UserInfo(Long id, String firstName, String lastName, String username, String email, String password,
			String roles, List<QuizScores> quizScores) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
		this.quizScores = quizScores;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	protected UserInfo() {
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<QuizScores> getQuizScores() {
		return quizScores;
	}

	public void setQuizScores(List<QuizScores> quizScores) {
		this.quizScores = quizScores;
	}

	public String getRoles() {
		return roles;
	}

	public void setRoles(String roles) {
		this.roles = roles;
	}

}
