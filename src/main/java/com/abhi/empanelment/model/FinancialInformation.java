package com.abhi.empanelment.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "financial_information")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class FinancialInformation
{
    @Id
    @NotBlank
    private String workflowNo;
    @NotBlank
    private String accountType;
    @NotBlank
    private String ifscCode;
    @NotBlank
    private String branchName;
    @NotBlank
    private String accountNumber;
    @NotBlank
    private String beneficiaryName;
    private String gstNo;
    @NotBlank
    private String panNo;
    private String legalName;
    private String tradeName;
    private String taxRegistrationNo;
    private String tinUnderValueAddedTax;
    private String lowerTDS;
    private String lowerTDSLimit;
    private String providerRemarks;
    private String exemptionStartDate;
    private String exemptionEndDate;
    private String tinNo;
    @NotBlank
    private String isPanValid;
    @NotBlank
    private String isPanAutoApproved;
    @NotBlank
    private String eligibleHighTds;
    @NotBlank
    private String pennyDropResult;
    @NotBlank
    private String isBeneficiaryNameMatch;
    @NotBlank
    private String bankName;
    @NotBlank
    private String address;
    @NotBlank
    private String micrCode;
    @NotBlank
    private String occupationType;
    @NotBlank
    private String panHolderName;
    private String paymentTerm;
    private String ratelistType;
    private String mouDiscount;
	public String getWorkflowNo() {
		return workflowNo;
	}
	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getBeneficiaryName() {
		return beneficiaryName;
	}
	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}
	public String getGstNo() {
		return gstNo;
	}
	public void setGstNo(String gstNo) {
		this.gstNo = gstNo;
	}
	public String getPanNo() {
		return panNo;
	}
	public void setPanNo(String panNo) {
		this.panNo = panNo;
	}
	public String getLegalName() {
		return legalName;
	}
	public void setLegalName(String legalName) {
		this.legalName = legalName;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getTaxRegistrationNo() {
		return taxRegistrationNo;
	}
	public void setTaxRegistrationNo(String taxRegistrationNo) {
		this.taxRegistrationNo = taxRegistrationNo;
	}
	public String getTinUnderValueAddedTax() {
		return tinUnderValueAddedTax;
	}
	public void setTinUnderValueAddedTax(String tinUnderValueAddedTax) {
		this.tinUnderValueAddedTax = tinUnderValueAddedTax;
	}
	public String getLowerTDS() {
		return lowerTDS;
	}
	public void setLowerTDS(String lowerTDS) {
		this.lowerTDS = lowerTDS;
	}
	public String getLowerTDSLimit() {
		return lowerTDSLimit;
	}
	public void setLowerTDSLimit(String lowerTDSLimit) {
		this.lowerTDSLimit = lowerTDSLimit;
	}
	public String getProviderRemarks() {
		return providerRemarks;
	}
	public void setProviderRemarks(String providerRemarks) {
		this.providerRemarks = providerRemarks;
	}
	public String getExemptionStartDate() {
		return exemptionStartDate;
	}
	public void setExemptionStartDate(String exemptionStartDate) {
		this.exemptionStartDate = exemptionStartDate;
	}
	public String getExemptionEndDate() {
		return exemptionEndDate;
	}
	public void setExemptionEndDate(String exemptionEndDate) {
		this.exemptionEndDate = exemptionEndDate;
	}
	public String getTinNo() {
		return tinNo;
	}
	public void setTinNo(String tinNo) {
		this.tinNo = tinNo;
	}
	public String getIsPanValid() {
		return isPanValid;
	}
	public void setIsPanValid(String isPanValid) {
		this.isPanValid = isPanValid;
	}
	public String getIsPanAutoApproved() {
		return isPanAutoApproved;
	}
	public void setIsPanAutoApproved(String isPanAutoApproved) {
		this.isPanAutoApproved = isPanAutoApproved;
	}
	public String getEligibleHighTds() {
		return eligibleHighTds;
	}
	public void setEligibleHighTds(String eligibleHighTds) {
		this.eligibleHighTds = eligibleHighTds;
	}
	public String getPennyDropResult() {
		return pennyDropResult;
	}
	public void setPennyDropResult(String pennyDropResult) {
		this.pennyDropResult = pennyDropResult;
	}
	public String getIsBeneficiaryNameMatch() {
		return isBeneficiaryNameMatch;
	}
	public void setIsBeneficiaryNameMatch(String isBeneficiaryNameMatch) {
		this.isBeneficiaryNameMatch = isBeneficiaryNameMatch;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMicrCode() {
		return micrCode;
	}
	public void setMicrCode(String micrCode) {
		this.micrCode = micrCode;
	}
	public String getOccupationType() {
		return occupationType;
	}
	public void setOccupationType(String occupationType) {
		this.occupationType = occupationType;
	}
	public String getPanHolderName() {
		return panHolderName;
	}
	public void setPanHolderName(String panHolderName) {
		this.panHolderName = panHolderName;
	}
	public String getPaymentTerm() {
		return paymentTerm;
	}
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	public String getRatelistType() {
		return ratelistType;
	}
	public void setRatelistType(String ratelistType) {
		this.ratelistType = ratelistType;
	}
	public String getMouDiscount() {
		return mouDiscount;
	}
	public void setMouDiscount(String mouDiscount) {
		this.mouDiscount = mouDiscount;
	}
}