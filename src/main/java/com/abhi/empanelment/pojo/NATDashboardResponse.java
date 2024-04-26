package com.abhi.empanelment.pojo;

import java.util.Date;

public class NATDashboardResponse {
	
	public String state;
	public Long totalReqRaisedCount;
	public Long pendingWithNATCount;
	public Long qc2PendingCaseCount;
	
	public NATDashboardResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Long getTotalReqRaisedCount() {
		return totalReqRaisedCount;
	}

	public void setTotalReqRaisedCount(Long totalReqRaisedCount) {
		this.totalReqRaisedCount = totalReqRaisedCount;
	}

	public Long getPendingWithNATCount() {
		return pendingWithNATCount;
	}

	public void setPendingWithNATCount(Long pendingWithNATCount) {
		this.pendingWithNATCount = pendingWithNATCount;
	}

	public Long getQc2PendingCaseCount() {
		return qc2PendingCaseCount;
	}

	public void setQc2PendingCaseCount(Long qc2PendingCaseCount) {
		this.qc2PendingCaseCount = qc2PendingCaseCount;
	}
}
