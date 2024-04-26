package com.abhi.empanelment.message;

import java.util.Date;

public class ResponseFile {
	private String workflowNo;
	private String name;
	private String url;
	private String type;
	private long size;
	private String docID;
	private String userID;
	private Date uploadDate;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getDocID() {
		return docID;
	}

	public void setDocID(String docID) {
		this.docID = docID;
	}

	public ResponseFile(String workflowNo, String docID, String userID, String name, String url, String type, long size,
			Date uploadDate) {
		this.workflowNo = workflowNo;
		this.docID = docID;
		this.userID = userID;
		this.name = name;
		this.url = url;
		this.type = type;
		this.size = size;
		this.uploadDate = uploadDate;

	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public String getWorkflowNo() {
		return workflowNo;
	}

	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}
}
