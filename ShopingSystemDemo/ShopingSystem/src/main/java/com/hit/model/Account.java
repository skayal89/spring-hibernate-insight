package com.hit.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity  
@Table(name = "Account") 
public class Account {

	@Id  
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name = "ACCOUNT_ID")
	private int accountId;
	
	@Column(name = "BillingAddress")
	private String billingAddress;
	
	@Column(name = "Open")
	private Date open;
	
	@Column(name = "Close")
	private Date close;
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(String billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Date getOpen() {
		return open;
	}
	public void setOpen(Date open) {
		this.open = open;
	}
	public Date getClose() {
		return close;
	}
	public void setClose(Date close) {
		this.close = close;
	}
	
	
}
