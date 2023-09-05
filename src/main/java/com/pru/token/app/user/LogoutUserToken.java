package com.pru.token.app.user;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Document(collection = "token")
public class LogoutUserToken {
	
	@Id
	private String tId;
	@Indexed(unique = true)
	private String empId;
	private boolean logout;
	private String token;
}
