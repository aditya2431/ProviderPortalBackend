package com.abhi.empanelment.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "discount_details")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class Discount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String workflowNo;
	private String discountType;
	private String claimPaymentTAT;
	private String softCopySettlement;
	private String ipdOpd;
	private String discountPercentage;
	private String discountOn;
	private String exclusionLevel1;
	private String inclusionLevel1;
	private Date validatyFrom;
	private Date validityTo;
	private String thresholdLimit;
	private String exclusionLeveL2L3;
	private String inclusionLeveL2L3;
	private String actions;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWorkflowNo() {
		return workflowNo;
	}
	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}
	public String getDiscountType() {
		return discountType;
	}
	public void setDiscountType(String discountType) {
		this.discountType = discountType;
	}
	public String getClaimPaymentTAT() {
		return claimPaymentTAT;
	}
	public void setClaimPaymentTAT(String claimPaymentTAT) {
		this.claimPaymentTAT = claimPaymentTAT;
	}
	public String getSoftCopySettlement() {
		return softCopySettlement;
	}
	public void setSoftCopySettlement(String softCopySettlement) {
		this.softCopySettlement = softCopySettlement;
	}
	public String getIpdOpd() {
		return ipdOpd;
	}
	public void setIpdOpd(String ipdOpd) {
		this.ipdOpd = ipdOpd;
	}
	public String getDiscountPercentage() {
		return discountPercentage;
	}
	public void setDiscountPercentage(String discountPercentage) {
		this.discountPercentage = discountPercentage;
	}
	public String getDiscountOn() {
		return discountOn;
	}
	public void setDiscountOn(String discountOn) {
		this.discountOn = discountOn;
	}
	public String getExclusionLevel1() {
		return exclusionLevel1;
	}
	public void setExclusionLevel1(String exclusionLevel1) {
		this.exclusionLevel1 = exclusionLevel1;
	}
	public String getInclusionLevel1() {
		return inclusionLevel1;
	}
	public void setInclusionLevel1(String inclusionLevel1) {
		this.inclusionLevel1 = inclusionLevel1;
	}
	public Date getValidatyFrom() {
		return validatyFrom;
	}
	public void setValidatyFrom(Date validatyFrom) {
		this.validatyFrom = validatyFrom;
	}
	public Date getValidityTo() {
		return validityTo;
	}
	public void setValidityTo(Date validityTo) {
		this.validityTo = validityTo;
	}
	public String getThresholdLimit() {
		return thresholdLimit;
	}
	public void setThresholdLimit(String thresholdLimit) {
		this.thresholdLimit = thresholdLimit;
	}
	public String getExclusionLeveL2L3() {
		return exclusionLeveL2L3;
	}
	public void setExclusionLeveL2L3(String exclusionLeveL2L3) {
		this.exclusionLeveL2L3 = exclusionLeveL2L3;
	}
	public String getInclusionLeveL2L3() {
		return inclusionLeveL2L3;
	}
	public void setInclusionLeveL2L3(String inclusionLeveL2L3) {
		this.inclusionLeveL2L3 = inclusionLeveL2L3;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
}