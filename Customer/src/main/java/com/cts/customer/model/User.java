package com.cts.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Account Creation Status Model with required attributes
//Using Getter-Setter notations of Lombok

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private int id;

	private String userName;
	
	private String password;
	
	private Integer custId;
	
	private String role;
	
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	//Parameterized Constructor
	public User(String userName, String password, Integer custId, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.custId = custId;
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
}
