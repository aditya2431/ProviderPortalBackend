package com.abhi.empanelment.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "mou_document_details")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class MOUDocument {

	@Id
    @NotBlank
    private String workflowNo;
	@NotBlank
    private String rmAdId;
	@NotBlank
	private String agreementMadeOn;
	@NotBlank
	private String hospitalName;
	@NotBlank
	private String hospitalAddress;
	@NotBlank
	private String representedBy;
	@NotBlank
	private String signedBy;
	@NotBlank
	private String serviceLevelDiscount;
	@NotBlank
	private String discountValidityFrom;
	@NotBlank
	private String discountValidityTo;
	@NotBlank
	private String opdDiscount;
	@NotBlank
	private String diagnosticTestDiscount;
	@NotBlank
	private String pharmacyDiscount;
	private String epdForPayment5Days;
	private String epdForPayment7Days;
	private String epdForPayment10Days;
	private String epdForPayment15Days;
	private String epdForPayment20Days;
	private String epdForPayment5DaysDesc;
	private String epdForPayment7DaysDesc;
	private String epdForPayment10DaysDesc;
	private String epdForPayment15DaysDesc;
	private String epdForPayment20DaysDesc;
	public String getWorkflowNo() {
		return workflowNo;
	}
	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}
	public String getRmAdId() {
		return rmAdId;
	}
	public void setRmAdId(String rmAdId) {
		this.rmAdId = rmAdId;
	}
	public String getAgreementMadeOn() {
		return agreementMadeOn;
	}
	public void setAgreementMadeOn(String agreementMadeOn) {
		this.agreementMadeOn = agreementMadeOn;
	}
	public String getHospitalName() {
		return hospitalName;
	}
	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}
	public String getHospitalAddress() {
		return hospitalAddress;
	}
	public void setHospitalAddress(String hospitalAddress) {
		this.hospitalAddress = hospitalAddress;
	}
	public String getRepresentedBy() {
		return representedBy;
	}
	public void setRepresentedBy(String representedBy) {
		this.representedBy = representedBy;
	}
	public String getSignedBy() {
		return signedBy;
	}
	public void setSignedBy(String signedBy) {
		this.signedBy = signedBy;
	}
	public String getServiceLevelDiscount() {
		return serviceLevelDiscount;
	}
	public void setServiceLevelDiscount(String serviceLevelDiscount) {
		this.serviceLevelDiscount = serviceLevelDiscount;
	}
	public String getDiscountValidityFrom() {
		return discountValidityFrom;
	}
	public void setDiscountValidityFrom(String discountValidityFrom) {
		this.discountValidityFrom = discountValidityFrom;
	}
	public String getDiscountValidityTo() {
		return discountValidityTo;
	}
	public void setDiscountValidityTo(String discountValidityTo) {
		this.discountValidityTo = discountValidityTo;
	}
	public String getOpdDiscount() {
		return opdDiscount;
	}
	public void setOpdDiscount(String opdDiscount) {
		this.opdDiscount = opdDiscount;
	}
	public String getDiagnosticTestDiscount() {
		return diagnosticTestDiscount;
	}
	public void setDiagnosticTestDiscount(String diagnosticTestDiscount) {
		this.diagnosticTestDiscount = diagnosticTestDiscount;
	}
	public String getPharmacyDiscount() {
		return pharmacyDiscount;
	}
	public void setPharmacyDiscount(String pharmacyDiscount) {
		this.pharmacyDiscount = pharmacyDiscount;
	}
	public String getEpdForPayment5Days() {
		return epdForPayment5Days;
	}
	public void setEpdForPayment5Days(String epdForPayment5Days) {
		this.epdForPayment5Days = epdForPayment5Days;
	}
	public String getEpdForPayment7Days() {
		return epdForPayment7Days;
	}
	public void setEpdForPayment7Days(String epdForPayment7Days) {
		this.epdForPayment7Days = epdForPayment7Days;
	}
	public String getEpdForPayment10Days() {
		return epdForPayment10Days;
	}
	public void setEpdForPayment10Days(String epdForPayment10Days) {
		this.epdForPayment10Days = epdForPayment10Days;
	}
	public String getEpdForPayment15Days() {
		return epdForPayment15Days;
	}
	public void setEpdForPayment15Days(String epdForPayment15Days) {
		this.epdForPayment15Days = epdForPayment15Days;
	}
	public String getEpdForPayment20Days() {
		return epdForPayment20Days;
	}
	public void setEpdForPayment20Days(String epdForPayment20Days) {
		this.epdForPayment20Days = epdForPayment20Days;
	}
	public String getEpdForPayment5DaysDesc() {
		return epdForPayment5DaysDesc;
	}
	public void setEpdForPayment5DaysDesc(String epdForPayment5DaysDesc) {
		this.epdForPayment5DaysDesc = epdForPayment5DaysDesc;
	}
	public String getEpdForPayment7DaysDesc() {
		return epdForPayment7DaysDesc;
	}
	public void setEpdForPayment7DaysDesc(String epdForPayment7DaysDesc) {
		this.epdForPayment7DaysDesc = epdForPayment7DaysDesc;
	}
	public String getEpdForPayment10DaysDesc() {
		return epdForPayment10DaysDesc;
	}
	public void setEpdForPayment10DaysDesc(String epdForPayment10DaysDesc) {
		this.epdForPayment10DaysDesc = epdForPayment10DaysDesc;
	}
	public String getEpdForPayment15DaysDesc() {
		return epdForPayment15DaysDesc;
	}
	public void setEpdForPayment15DaysDesc(String epdForPayment15DaysDesc) {
		this.epdForPayment15DaysDesc = epdForPayment15DaysDesc;
	}
	public String getEpdForPayment20DaysDesc() {
		return epdForPayment20DaysDesc;
	}
	public void setEpdForPayment20DaysDesc(String epdForPayment20DaysDesc) {
		this.epdForPayment20DaysDesc = epdForPayment20DaysDesc;
	}
}