package com.abhi.empanelment.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RTdetails {
	
	@JsonProperty("PolicyID") 
    public String policyID;
    @JsonProperty("AppNo") 
    public String appNo;
    public String alertID;
    public String channel_ID;
    @JsonProperty("Req_Id") 
    public String req_Id;
    public String field1;
    public String field2;
    public String field3;
    @JsonProperty("Alert_Mode") 
    public String alert_Mode;
    @JsonProperty("Alertdata") 
    public Alertdata alertdata;
	public RTdetails(String policyID, String appNo, String alertID, String channel_ID, String req_Id, String field1,
			String field2, String field3, String alert_Mode, Alertdata alertdata) {
		super();
		this.policyID = policyID;
		this.appNo = appNo;
		this.alertID = alertID;
		this.channel_ID = channel_ID;
		this.req_Id = req_Id;
		this.field1 = field1;
		this.field2 = field2;
		this.field3 = field3;
		this.alert_Mode = alert_Mode;
		this.alertdata = alertdata;
	}
}
