package com.abhi.empanelment.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.hibernate.validator.constraints.Length;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.abhi.empanelment.generator.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "sendlink_data")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class SendLink {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "workflow_seq")
	@GenericGenerator(name = "workflow_seq", strategy = "com.abhi.empanelment.model.WorkflowNoGenerator", parameters = {
			@Parameter(name = StringPrefixedSequenceIdGenerator.INCREMENT_PARAM, value = "1"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.VALUE_PREFIX_PARAMETER, value = "H24"),
			@Parameter(name = StringPrefixedSequenceIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%06d") })
	@Column(name = "workflow_no", updatable = false, nullable = false)
	private String workflowNo;

	@NotBlank
	private String workflowStatus;
	@NotBlank
	private String rohiniId;
	@NotNull
	private Date submissionDate;
	private Date queryRaisedDate;
	private Date rejectionDate;
	private Date mouGenerationDate;
	private Date qc1Date;
	@NotBlank
	private String rmAdId;
	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$")
	@NotBlank
	private String providerName;
	@Email(message = "Email is not valid", regexp = "[A-Za-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}")
	@NotBlank
	private String email;
	@Pattern(regexp = "^[a-zA-Z0-9.\\-\\/+=@_ ]*$")
	@NotBlank
	@Pattern(regexp="(^$|[0-9]{10})", message="only digits allowed for mobile number with max 10 chars")
	private String mobileNumber;
	@Pattern(regexp = "^[a-zA-Z0-9,.\\-\\/+=@_ ]*$",
            message = "special characters are not allowed for address")
	@NotBlank
	private String address;
	@Pattern(regexp = "^[a-zA-Z0-9,.\\-\\/+=@_ ]*$", message="special characters are not allowed for state")
	@NotBlank
	private String state;
	@Pattern(regexp = "^[1-9][0-9]{5}$", message="only nubers are allowed for pincode")
	@NotBlank
	private String pincode;
	private String queryReason;
	private String rejectionReason;
	@Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)?$", message="only alphnumeric inputs are allowed for contact person")
	@NotBlank
	private String contactPerson;
	@Pattern(regexp = "^[a-zA-Z]+(\\s[a-zA-Z]+)?$", message="only alphnumeric inputs are allowed for RM Name")
	@NotBlank
	private String rmName;
	public String getWorkflowNo() {
		return workflowNo;
	}
	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}
	public String getWorkflowStatus() {
		return workflowStatus;
	}
	public void setWorkflowStatus(String workflowStatus) {
		this.workflowStatus = workflowStatus;
	}
	public String getRohiniId() {
		return rohiniId;
	}
	public void setRohiniId(String rohiniId) {
		this.rohiniId = rohiniId;
	}
	public Date getSubmissionDate() {
		return submissionDate;
	}
	public void setSubmissionDate(Date submissionDate) {
		this.submissionDate = submissionDate;
	}
	public Date getQueryRaisedDate() {
		return queryRaisedDate;
	}
	public void setQueryRaisedDate(Date queryRaisedDate) {
		this.queryRaisedDate = queryRaisedDate;
	}
	public Date getRejectionDate() {
		return rejectionDate;
	}
	public void setRejectionDate(Date rejectionDate) {
		this.rejectionDate = rejectionDate;
	}
	public Date getMouGenerationDate() {
		return mouGenerationDate;
	}
	public void setMouGenerationDate(Date mouGenerationDate) {
		this.mouGenerationDate = mouGenerationDate;
	}
	public Date getQc1Date() {
		return qc1Date;
	}
	public void setQc1Date(Date qc1Date) {
		this.qc1Date = qc1Date;
	}
	public String getRmAdId() {
		return rmAdId;
	}
	public void setRmAdId(String rmAdId) {
		this.rmAdId = rmAdId;
	}
	public String getProviderName() {
		return providerName;
	}
	public void setProviderName(String providerName) {
		this.providerName = providerName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getQueryReason() {
		return queryReason;
	}
	public void setQueryReason(String queryReason) {
		this.queryReason = queryReason;
	}
	public String getRejectionReason() {
		return rejectionReason;
	}
	public void setRejectionReason(String rejectionReason) {
		this.rejectionReason = rejectionReason;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
}
