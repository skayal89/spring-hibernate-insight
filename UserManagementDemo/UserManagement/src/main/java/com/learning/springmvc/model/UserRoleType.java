package com.learning.springmvc.model;

public enum UserRoleType {
	USER("ROLE_USER"),
	ADMIN("ROLE_ADMIN"),
	DBA("ROLE_DBA");
	
	String userRole;
	
	private UserRoleType(String userRole) {
		this.userRole=userRole;
	}
	
	public String getUserRoleType() {
		return userRole;
		
	}
}
