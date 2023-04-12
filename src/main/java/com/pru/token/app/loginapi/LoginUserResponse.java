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
	private String firstName;
	private String lastName;
	private String userId;
	private Reviewer reviewer;
	private Manager manager;
	private String token;
	
}
