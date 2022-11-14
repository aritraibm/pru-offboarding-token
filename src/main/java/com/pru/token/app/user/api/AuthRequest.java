package com.pru.token.app.user.api;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class AuthRequest {
	@NotNull @Email 
	private String email;
	
	@NotNull 
	private String password;

	public AuthRequest() {
		
	}
	
	public AuthRequest(String email,String password) {
		this.email = email;
		this.password = password;
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
	
	
}
