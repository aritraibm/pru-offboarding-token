package com.pru.token.app.user.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {

	private String email;
	
	private String employeeId;
	
	private String reviewerEmpId;
	
	private String managerEmpId;
	
	private String firstName;

	private String lastName;
	
	private String password;
	
	private String roleId;
	
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
