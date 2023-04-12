package com.pru.token.app.user.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestUser {

	private String id;
	
	private String email;
	
	private String employeeId;
	
	private String reviewerEmpId;
	
	private String managerEmpId;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String roleId;

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

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getReviewerEmpId() {
		return reviewerEmpId;
	}

	public void setReviewerEmpId(String reviewerEmpId) {
		this.reviewerEmpId = reviewerEmpId;
	}

	public String getManagerEmpId() {
		return managerEmpId;
	}

	public void setManagerEmpId(String managerEmpId) {
		this.managerEmpId = managerEmpId;
	}

//	public String getUserName() {
//		return userName;
//	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	
	
	
	
	
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	
}
