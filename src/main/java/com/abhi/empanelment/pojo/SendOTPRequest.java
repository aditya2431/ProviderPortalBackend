package com.abhi.empanelment.pojo;

public class SendOTPRequest {
	
	public String email;
	public String workflowNo;
	public String providerName;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWorkflowNo() {
		return workflowNo;
	}
	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}

}
