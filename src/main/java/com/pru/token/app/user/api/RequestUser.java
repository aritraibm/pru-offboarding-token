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
	
	private String userName;
	
	private String firstName;
	
	private String lastName;
	
	private String password;
	
	private String roleId;
	
	public RequestUser(String email, String employeeId, String reviewerEmpId, String managerEmpId, String userName,
			String password, String roleId) {
		super();
		this.email = email;
		this.employeeId = employeeId;
		this.reviewerEmpId = reviewerEmpId;
		this.managerEmpId = managerEmpId;
		this.userName = userName;
		this.password = password;
		this.roleId = roleId;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

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
	
	public RequestUser(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLastname() {
		// TODO Auto-generated method stub
		return lastName;
	}

	public String getFirstname() {
		// TODO Auto-generated method stub
		return firstName;
	}

	
}
