package com.hit.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity  
@Table(name = "User")  
@Inheritance(strategy=InheritanceType.JOINED)  
public class User {
	
	@Id  
	// Don't use @GeneratedValue if we want custom ID to be set.
	//@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name = "USER_ID")
	@NotEmpty
	@NotNull
	private String userId;
	
	@Column(name = "Password")
	@NotEmpty
	@NotNull
	private String password;
	
	@Column(name = "LoginStatus")
	@Enumerated(EnumType.STRING) 
	private LoginStatus loginStatus;
	
	/*@Column(name="ACCOUNT_ID")
	private int accountId;*/
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;
	
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LoginStatus getLoginStatus() {
		return loginStatus;
	}
	public void setLoginStatus(LoginStatus loginStatus) {
		this.loginStatus = loginStatus;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	
	
	
}
