package com.pru.token.app.user.api;

public class UserDTO {
	private String id;
	private String email;

	public UserDTO() {
	}

	public UserDTO(String id, String email) {
		this.id = id;
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
