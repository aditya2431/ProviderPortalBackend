package com.abhi.empanelment.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.persistence.Id;

import com.abhi.empanelment.generator.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name = "general_information")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class GeneralInformation
{
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private int id;
	@Id
	@NotBlank
    @Column(name = "workflow_no", updatable = false, nullable = false)
    private String workflowNo;
    
    
    @NotBlank
    private String providerName;
    @NotBlank
    private String addressLine1;
    private String addressLine2;
    @NotBlank
    private String zipcode;
    @NotBlank
    private String city;
    @NotBlank
    private String district;
    @NotBlank
    private String state;
    @NotBlank
    private String country;
    @NotBlank
    private String latitude;
    @NotBlank
    private String longitude;
    @NotNull
    private boolean complianceFlag;
    @NotNull
    private boolean licenceFlag;
    @NotBlank
    private String contactPerson;
    @NotBlank
    private String landlineNo;
    @NotBlank
    private String mobileNo;
    @NotBlank
    private String emailId;
    private String alternateEmailId;
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
	public String getAddressLine1() {
		return addressLine1;
	}
	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}
	public String getAddressLine2() {
		return addressLine2;
	}
	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}
	public String getZipcode() {
		return zipcode;
	}
	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public boolean isComplianceFlag() {
		return complianceFlag;
	}
	public void setComplianceFlag(boolean complianceFlag) {
		this.complianceFlag = complianceFlag;
	}
	public boolean isLicenceFlag() {
		return licenceFlag;
	}
	public void setLicenceFlag(boolean licenceFlag) {
		this.licenceFlag = licenceFlag;
	}
	public String getContactPerson() {
		return contactPerson;
	}
	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}
	public String getLandlineNo() {
		return landlineNo;
	}
	public void setLandlineNo(String landlineNo) {
		this.landlineNo = landlineNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getAlternateEmailId() {
		return alternateEmailId;
	}
	public void setAlternateEmailId(String alternateEmailId) {
		this.alternateEmailId = alternateEmailId;
	}
}