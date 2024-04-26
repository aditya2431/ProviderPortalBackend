package com.abhi.empanelment.model;

import javax.validation.constraints.NotBlank;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "provider_information")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class ProviderInformation
{
    @Id
    @NotBlank
    private String workflowNo;
    @NotBlank
    private String rohiniId;
    @NotBlank
    private String rohiniCodeExpiryDate;
    @NotBlank
    private String specialization;
    @NotBlank
    private String subSpecialization;
    private String hfrId;
    @NotBlank
    private String registrationNumber;
    @NotBlank
    private String placeOfRegistration;
    @NotBlank
    private String registrationExpiryDate;
    @NotBlank
    private String issuingAuthority;
    @NotBlank
    private String levelOfCare;
    @NotBlank
    private String ownershipType;
    private String agreementStartDate;
    private String agreementEndDate;
	public String getWorkflowNo() {
		return workflowNo;
	}
	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}
	public String getRohiniId() {
		return rohiniId;
	}
	public void setRohiniId(String rohiniId) {
		this.rohiniId = rohiniId;
	}
	public String getRohiniCodeExpiryDate() {
		return rohiniCodeExpiryDate;
	}
	public void setRohiniCodeExpiryDate(String rohiniCodeExpiryDate) {
		this.rohiniCodeExpiryDate = rohiniCodeExpiryDate;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public String getSubSpecialization() {
		return subSpecialization;
	}
	public void setSubSpecialization(String subSpecialization) {
		this.subSpecialization = subSpecialization;
	}
	public String getHfrId() {
		return hfrId;
	}
	public void setHfrId(String hfrId) {
		this.hfrId = hfrId;
	}
	public String getRegistrationNumber() {
		return registrationNumber;
	}
	public void setRegistrationNumber(String registrationNumber) {
		this.registrationNumber = registrationNumber;
	}
	public String getPlaceOfRegistration() {
		return placeOfRegistration;
	}
	public void setPlaceOfRegistration(String placeOfRegistration) {
		this.placeOfRegistration = placeOfRegistration;
	}
	public String getRegistrationExpiryDate() {
		return registrationExpiryDate;
	}
	public void setRegistrationExpiryDate(String registrationExpiryDate) {
		this.registrationExpiryDate = registrationExpiryDate;
	}
	public String getIssuingAuthority() {
		return issuingAuthority;
	}
	public void setIssuingAuthority(String issuingAuthority) {
		this.issuingAuthority = issuingAuthority;
	}
	public String getLevelOfCare() {
		return levelOfCare;
	}
	public void setLevelOfCare(String levelOfCare) {
		this.levelOfCare = levelOfCare;
	}
	public String getOwnershipType() {
		return ownershipType;
	}
	public void setOwnershipType(String ownershipType) {
		this.ownershipType = ownershipType;
	}
	public String getAgreementStartDate() {
		return agreementStartDate;
	}
	public void setAgreementStartDate(String agreementStartDate) {
		this.agreementStartDate = agreementStartDate;
	}
	public String getAgreementEndDate() {
		return agreementEndDate;
	}
	public void setAgreementEndDate(String agreementEndDate) {
		this.agreementEndDate = agreementEndDate;
	}
    
}