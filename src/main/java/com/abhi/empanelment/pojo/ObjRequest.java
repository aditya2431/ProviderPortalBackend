package com.abhi.empanelment.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ObjRequest{
    @JsonProperty("LongURL") 
    public String longURL;
    @JsonProperty("ExpiryDay") 
    public String expiryDay;
    @JsonProperty("UserId") 
    public String userId;
    @JsonProperty("Password") 
    public String password;
    
    
	public ObjRequest(String longURL, String expiryDay, String userId, String password) {
		super();
		this.longURL = longURL;
		this.expiryDay = expiryDay;
		this.userId = userId;
		this.password = password;
	}
	
	public String getLongURL() {
		return longURL;
	}
	public void setLongURL(String longURL) {
		this.longURL = longURL;
	}
	public String getExpiryDay() {
		return expiryDay;
	}
	public void setExpiryDay(String expiryDay) {
		this.expiryDay = expiryDay;
	}
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
    
    
}