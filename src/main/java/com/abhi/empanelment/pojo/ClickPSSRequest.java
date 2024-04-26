package com.abhi.empanelment.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ClickPSSRequest {
	
	@JsonProperty("RTdetails") 
    public RTdetails rTdetails;

	public ClickPSSRequest(RTdetails rTdetails) {
		super();
		this.rTdetails = rTdetails;
	}
}
