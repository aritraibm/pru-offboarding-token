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
@Document(collection = "reviewer")
public class Reviewer {
	
	@Id
	private String id;
	
	private String firstName;
	private String lastName;
	@Indexed(unique = true)
	private String email;
	@Indexed(unique = true)
	private String employeeId;

	private String managerEmpId;

	public Reviewer(String firstName, String lastName, String email, String employeeId, String managerEmpId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.employeeId = employeeId;
		this.managerEmpId = managerEmpId;
	}
}
