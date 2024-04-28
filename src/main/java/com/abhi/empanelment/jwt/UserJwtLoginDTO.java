package com.abhi.empanelment.jwt;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

public class UserJwtLoginDTO implements Serializable{

	@NotNull(message = "UserName Is Wrong!")
	private String userName;
	@NotNull(message = "UserPassword Is Wrong!")
	private String password;
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
	
	
}
