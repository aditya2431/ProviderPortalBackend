package com.abhi.empanelment.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Table(name="Token")
@Entity
public class Token implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer  id;
	
	@Column(name = "token")
	private String token;
	@Column(name = "user_name")
	private String username;
	@Column(name = "is_logout")
	private boolean isLogout;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public boolean isLogout() {
		return isLogout;
	}
	public void setLogout(boolean isLogout) {
		this.isLogout = isLogout;
	}
	
	
}
