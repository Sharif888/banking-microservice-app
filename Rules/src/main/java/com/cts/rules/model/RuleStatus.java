package com.cts.rules.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Rule Status Model with required attributes
//Using Getter-Setter notations of Lombok
//
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
public class RuleStatus {
	private String status;

	public RuleStatus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public RuleStatus(String status) {
		super();
		this.status = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
}
