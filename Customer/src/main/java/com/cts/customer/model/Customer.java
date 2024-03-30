package com.cts.customer.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//Customer Model with required attributes
//Using Getter-Setter notations of Lombok

//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {
	
	@Id
	private Integer custId;
	
	@Column(name = "Name")
	private String custName;
	
	@Column(name = "UserName")
	private String custUname;
	
	@Column(name = "Password")
	private String custPass;
	
	@Column(name = "Address")
	private String custAddress;
	
	@Column(name = "PanNo")
	private String custPanNo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "DOB")
	private LocalDate custDob;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Customer(Integer custId, String custName, String custUname, String custPass, String custAddress,
			String custPanNo, LocalDate custDob) {
		super();
		this.custId = custId;
		this.custName = custName;
		this.custUname = custUname;
		this.custPass = custPass;
		this.custAddress = custAddress;
		this.custPanNo = custPanNo;
		this.custDob = custDob;
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getCustUname() {
		return custUname;
	}

	public void setCustUname(String custUname) {
		this.custUname = custUname;
	}

	public String getCustPass() {
		return custPass;
	}

	public void setCustPass(String custPass) {
		this.custPass = custPass;
	}

	public String getCustAddress() {
		return custAddress;
	}

	public void setCustAddress(String custAddress) {
		this.custAddress = custAddress;
	}

	public String getCustPanNo() {
		return custPanNo;
	}

	public void setCustPanNo(String custPanNo) {
		this.custPanNo = custPanNo;
	}

	public LocalDate getCustDob() {
		return custDob;
	}

	public void setCustDob(LocalDate custDob) {
		this.custDob = custDob;
	}
	
}
