package com.abhi.empanelment.pojo;

import java.util.Date;

public class ClickPSSMOURequest {
	
	public String documentId;
	public String workflowNo;
	public String agreementMadeOn;
	public String hospitalName;
	public String hospitalMailId;
	public String ccMailId;
	public String hospitalAddress;
	public String representedBy;
	public String signedBy;
	public String serviceLevelDiscount;
	public String discountValidityFrom;
	public String discountValidityTo;
	public String opdDiscount;
	public String diagnosticTestDiscount;
	public String pharmacyDiscount;
	public String epdForPayment5Days;
	public String epdForPayment7Days;
	public String epdForPayment10Days;
	public String epdForPayment15Days;
	public String epdForPayment20Days;
	public String epdForPayment5DaysDesc;
	public String epdForPayment7DaysDesc;
	public String epdForPayment10DaysDesc;
	public String epdForPayment15DaysDesc;
	public String epdForPayment20DaysDesc;
	
	public ClickPSSMOURequest(String documentId, String workflowNo, String date, String hospitalName,
			String hospitalMailId, String ccMailId, String hospitalAddress, String representedBy, String signedBy,
			String serviceLevelDiscount, String discountValidityFrom, String discountValidityTo, String opdDiscount,
			String diagnosticTestDiscount, String pharmacyDiscount, String epdForPayment5Days,
			String epdForPayment7Days, String epdForPayment10Days, String epdForPayment15Days,
			String epdForPayment20Days, String epdForPayment5DaysDesc, String epdForPayment7DaysDesc,
			String epdForPayment10DaysDesc, String epdForPayment15DaysDesc, String epdForPayment20DaysDesc) {
		super();
		this.documentId = documentId;
		this.workflowNo = workflowNo;
		this.agreementMadeOn = date;
		this.hospitalName = hospitalName;
		this.hospitalMailId = hospitalMailId;
		this.ccMailId = ccMailId;
		this.hospitalAddress = hospitalAddress;
		this.representedBy = representedBy;
		this.signedBy = signedBy;
		this.serviceLevelDiscount = serviceLevelDiscount;
		this.discountValidityFrom = discountValidityFrom;
		this.discountValidityTo = discountValidityTo;
		this.opdDiscount = opdDiscount;
		this.diagnosticTestDiscount = diagnosticTestDiscount;
		this.pharmacyDiscount = pharmacyDiscount;
		this.epdForPayment5Days = epdForPayment5Days;
		this.epdForPayment7Days = epdForPayment7Days;
		this.epdForPayment10Days = epdForPayment10Days;
		this.epdForPayment15Days = epdForPayment15Days;
		this.epdForPayment20Days = epdForPayment20Days;
		this.epdForPayment5DaysDesc = epdForPayment5DaysDesc;
		this.epdForPayment7DaysDesc = epdForPayment7DaysDesc;
		this.epdForPayment10DaysDesc = epdForPayment10DaysDesc;
		this.epdForPayment15DaysDesc = epdForPayment15DaysDesc;
		this.epdForPayment20DaysDesc = epdForPayment20DaysDesc;
	}

}
