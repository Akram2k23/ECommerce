package com.ecom.controller.model;


public class Model_SaveUserRequest {
	
	String username;
	String password;
	String firstName;
	String lastName;
	String mobile;
	String email;
	String address;
	
	public Model_SaveUserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Model_SaveUserRequest(String username, String password, String firstName, String lastName, String mobile,
			String email, String address) {
		super();
		this.username = username;
		this.password = password;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobile = mobile;
		this.email = email;
		this.address = address;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Model_SaveUserRequest [username=" + username + ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", mobile=" + mobile + ", email=" + email + ", address=" + address + "]";
	}

}
