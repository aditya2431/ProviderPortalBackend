package com.abhi.empanelment.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Alertdata {
	
	public String mobileno;
    public String emailId;
    public String bcc_emailId;
    @JsonProperty("AlertV1") 
    public String alertV1;
    @JsonProperty("AlertV2") 
    public String alertV2;
    @JsonProperty("AlertV3") 
    public String alertV3;
    @JsonProperty("AlertV4") 
    public String alertV4;
    @JsonProperty("AlertV5") 
    public String alertV5;
    @JsonProperty("AlertV6") 
    public String alertV6;
    @JsonProperty("AlertV7") 
    public String alertV7;
    @JsonProperty("AlertV8") 
    public String alertV8;
    @JsonProperty("AlertV9") 
    public String alertV9;
    @JsonProperty("AlertV10") 
    public String alertV10;
	public Alertdata(String mobileno, String emailId, String bcc_emailId, String alertV1, String alertV2, String alertV3, String alertV4,
			String alertV5, String alertV6, String alertV7, String alertV8, String alertV9, String alertV10) {
		super();
		this.mobileno = mobileno;
		this.emailId = emailId;
		this.bcc_emailId = bcc_emailId;
		this.alertV1 = alertV1;
		this.alertV2 = alertV2;
		this.alertV3 = alertV3;
		this.alertV4 = alertV4;
		this.alertV5 = alertV5;
		this.alertV6 = alertV6;
		this.alertV7 = alertV7;
		this.alertV8 = alertV8;
		this.alertV9 = alertV9;
		this.alertV10 = alertV10;
	}
    
}
