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
@Table(name = "epd_discount_details")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class EPDDiscount {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String workflowNo;
	private Date discountStartDate;
	private Date discountEndDare;
	private String discountDescIPD;
	private String discountPercentageIPD;
	private String ipdDiscountApplicableOn;
	private String earlyPaymentDiscServDays;
	private String earlyPaymentDiscServDiscount;
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
		workflowNo = workflowNo;
	}
	public Date getDiscountStartDate() {
		return discountStartDate;
	}
	public void setDiscountStartDate(Date discountStartDate) {
		this.discountStartDate = discountStartDate;
	}
	public Date getDiscountEndDare() {
		return discountEndDare;
	}
	public void setDiscountEndDare(Date discountEndDare) {
		this.discountEndDare = discountEndDare;
	}
	public String getDiscountDescIPD() {
		return discountDescIPD;
	}
	public void setDiscountDescIPD(String discountDescIPD) {
		this.discountDescIPD = discountDescIPD;
	}
	public String getDiscountPercentageIPD() {
		return discountPercentageIPD;
	}
	public void setDiscountPercentageIPD(String discountPercentageIPD) {
		this.discountPercentageIPD = discountPercentageIPD;
	}
	public String getIpdDiscountApplicableOn() {
		return ipdDiscountApplicableOn;
	}
	public void setIpdDiscountApplicableOn(String ipdDiscountApplicableOn) {
		this.ipdDiscountApplicableOn = ipdDiscountApplicableOn;
	}
	public String getEarlyPaymentDiscServDays() {
		return earlyPaymentDiscServDays;
	}
	public void setEarlyPaymentDiscServDays(String earlyPaymentDiscServDays) {
		this.earlyPaymentDiscServDays = earlyPaymentDiscServDays;
	}
	public String getEarlyPaymentDiscServDiscount() {
		return earlyPaymentDiscServDiscount;
	}
	public void setEarlyPaymentDiscServDiscount(String earlyPaymentDiscServDiscount) {
		this.earlyPaymentDiscServDiscount = earlyPaymentDiscServDiscount;
	}
	public String getActions() {
		return actions;
	}
	public void setActions(String actions) {
		this.actions = actions;
	}
}