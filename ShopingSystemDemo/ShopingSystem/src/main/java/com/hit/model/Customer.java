package com.hit.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity  
@Table(name="Customer")
@PrimaryKeyJoinColumn(name="USER_ID")
public class Customer extends User {
	
	@Column(name="Address") 
	private String address;
	
	@NotNull
	@NotEmpty
	@Column(name="Email") 
	private String email;
	
	@Column(name="Name") 
	private String name;
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
