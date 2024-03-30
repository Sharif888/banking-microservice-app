package com.cts.authorization.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "users")
public class User {
	
	public User(String userName, String password, Integer custId, String role) {
		super();
		this.userName = userName;
		this.password = password;
		this.custId = custId;
		this.role = role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(int id, String userName, String password, Integer custId, String role) {
		super();
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.custId = custId;
		this.role = role;
	}

	@Id
	@GeneratedValue
	private int id;
	
	private String userName;
	
	private String password;
	
	private Integer custId;
	
	private String role;

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
