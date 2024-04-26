package com.abhi.empanelment.pojo;

public class HypervergeTokenRequest {
	
    public String appId;
    public String appKey;
    public String expiry;
    
	public HypervergeTokenRequest(String appId, String appKey, String expiry) {
		super();
		this.appId = appId;
		this.appKey = appKey;
		this.expiry = expiry;
	}
}
