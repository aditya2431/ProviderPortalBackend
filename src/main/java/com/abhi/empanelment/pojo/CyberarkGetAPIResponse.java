package com.abhi.empanelment.pojo;

public class CyberarkGetAPIResponse {
	
    public String auth_time;
    public String aud;
    public String unique_name;
    public String sAMAccountName;
    public String sub;
    
	public String getAuth_time() {
		return auth_time;
	}
	public void setAuth_time(String auth_time) {
		this.auth_time = auth_time;
	}
	public String getAud() {
		return aud;
	}
	public void setAud(String aud) {
		this.aud = aud;
	}
	public String getUnique_name() {
		return unique_name;
	}
	public void setUnique_name(String unique_name) {
		this.unique_name = unique_name;
	}
	public String getsAMAccountName() {
		return sAMAccountName;
	}
	public void setsAMAccountName(String sAMAccountName) {
		this.sAMAccountName = sAMAccountName;
	}
	public String getSub() {
		return sub;
	}
	public void setSub(String sub) {
		this.sub = sub;
	}

}
