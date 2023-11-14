package com.login_jwt.model;

import org.springframework.stereotype.Component;

@Component
public class Login_user {
	
	
	private String name ;
		
	private String password ;
	
	public Login_user() {
		super();
	}

	public Login_user(String name, String password) {			
		super();
		this.name = name;
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}