package com.pru.token.app.loginapi;

import com.pru.token.app.user.Manager;
import com.pru.token.app.user.Reviewer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class LoginUserResponse {

	private String role;
	private String email;
	private String name;
	private String userId;
	private Reviewer reviewer;
	private Manager manager;
	private String token;
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public Reviewer getReviewer() {
		return reviewer;
	}
	public void setReviewer(Reviewer reviewer) {
		this.reviewer = reviewer;
	}
	public Manager getManager() {
		return manager;
	}
	public void setManager(Manager manager) {
		this.manager = manager;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

}
