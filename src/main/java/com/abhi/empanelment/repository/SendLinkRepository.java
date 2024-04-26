package com.abhi.empanelment.repository;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.abhi.empanelment.model.SendLink;

@Repository
public interface SendLinkRepository extends JpaRepository<SendLink, String>, SendLinkCustomRepository {
	@Query("SELECT a FROM SendLink a WHERE date_format(submission_date,'%Y/%m/%d') =  date(:submissionDate) and workflow_status = :workflowStatus ")
	public List<SendLink> findBySubmissionDate(String submissionDate, String workflowStatus);
	
	public SendLink findByWorkflowNo(String workflowNumber);
	
	public List<SendLink> findByEmail(String email);
	
	public List<SendLink> findByState(String state);
	
	@Query("SELECT a FROM SendLink a WHERE date_format(query_raised_date,'%Y/%m/%d') =  date(:queryDate) and workflow_status = :workflowStatus")
	public List<SendLink> findByQueryRaisedDate(String queryDate, String workflowStatus);
	
	@Query("SELECT a FROM SendLink a WHERE date_format(mou_generation_date,'%Y/%m/%d') =  date(:mouDate) and workflow_status = :workflowStatus")
	public List<SendLink> findByMouGenerationDate(String mouDate, String workflowStatus);
	
	@Query("SELECT a FROM SendLink a WHERE date_format(rejection_date,'%Y/%m/%d') =  date(:rejectionDate)")
	public List<SendLink> findByRejectionDate(String rejectionDate);

	public List<SendLink> findAll(Specification<SendLink> spec);

//	@Query("SELECT a.rm_ad_id, (SELECT COUNT(a) FROM SendLink a WHERE rm_ad_id = :rmAdId) as totalReqRaisedCount, (SELECT COUNT(a) FROM SendLink a WHERE rm_ad_id = :rmAdId and workflow_status = 'Init') as pendingWithProviderCount, (SELECT COUNT(a) FROM SendLink a WHERE rm_ad_id = :rmAdId and workflow_status = 'Submmit') as pendingWithNATCount, (SELECT COUNT(a) FROM SendLink a WHERE rm_ad_id = :rmAdId and workflow_status = 'QC1') as qc1ApprovedCaseCount, (SELECT COUNT(a) FROM SendLink a WHERE rm_ad_id = :rmAdId and workflow_status = 'Expire') as expiredCaseCount, (SELECT COUNT(a) FROM SendLink a WHERE rm_ad_id = :rmAdId and workflow_status = 'Reject') as rejectedCaseCount, (SELECT COUNT(a) FROM SendLink a WHERE rm_ad_id = :rmAdId and workflow_status = 'Query') as rejectedCaseCount FROM (SELECT DISTINCT rm_ad_id FROM SendLink) a")
//	public List<SendLink> fetchCount(String rmAdId);

	@Query("select  " + "(select count(b) from SendLink b  where rm_ad_id = :rmAdId) as totalReqRaisedCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'Init') as pendingWithProviderCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'Submit') as pendingWithNATCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'Query') as queryCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'QC1') as qc1ApprovedCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'MOU Generated') as mouStampingCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'Ratelist Upload') as ratelistUploadCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'Eligible For QC2') as pendingWithNATForQC2Count, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'QC2 Query') as qc2QueryCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'QC2') as qc2ApprovedCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'Expire') as expiredCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = :rmAdId and workflow_status = 'Reject') as rejectedCaseCount "
			+ "from SendLink a where rm_ad_id = :rmAdId ")
	public List<Object[]> getRMDashBoard(String rmAdId);

	@Query("SELECT distinct a.rmAdId, b.zmAdId,"
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId) as totalReqRaisedCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'Init') as pendingWithProviderCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'Submit') as pendingWithNATCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'Query') as queryCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'QC1') as qc1ApprovedCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'MOU Generated') as mouStampingCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'Ratelist Upload') as ratelistUploadCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'Eligible For QC2') as pendingWithNATForQC2Count, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'QC2 Query') as qc2QueryCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'QC2') as qc2ApprovedCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'Expire') as expiredCaseCount, "
			+ "(select count(b) from SendLink b  where rm_ad_id = a.rmAdId and workflow_status = 'Reject') as rejectedCaseCount "
			+ " FROM SendLink a, User b WHERE a.rmAdId = b.adId and b.zmAdId =  :zm ")
	public List<Object[]> getZMDashBoard(String zm);
	
	@Query("select a.state, (select count(1) from SendLink where state = a.state) as totalReqRaisedCount, "
			+ "(select count(1) from SendLink where workflow_status = 'Submit' and state = a.state) as pendingWithNATCount, "
			+ "(select count(1) from SendLink where workflow_status = 'Eligible For QC2' and state = a.state) as qc2PendingCaseCount "
			+ "FROM SendLink a group by a.state ")
	public List<Object[]> getNATDashBoard(); 

	@Query("select a.workflowNo, a.address, a.email, a.mobileNumber, a.pincode, a.providerName, "
			+ "a.qc1Date ,a.queryRaisedDate,a.queryReason,a.rejectionDate,a.rejectionReason, a.rmAdId,"
			+ "a.rohiniId,a.submissionDate,a.workflowStatus, b.addressLine1, b.addressLine2, b.city, "
			+ "b.complianceFlag, b.country, b.district, b.latitude, b.licenceFlag, b.longitude, b.providerName, "
			+ "b.state, b.zipcode, c.hfrId, c.issuingAuthority, c.levelOfCare, c.ownershipType, "
			+ "c.placeOfRegistration, c.registrationExpiryDate, c.registrationNumber, c.rohiniCodeExpiryDate, "
			+ "c.rohiniId, c.specialization, c.subSpecialization, d.accreditationReceived, d.averageAdmissionTime, "
			+ "d.averageDischargeTime, d.averageStayForMedicalCases, d.averageStayForSurgicalCases, "
			+ "d.cSectionRate, d.doctorToBedICURatio, d.doctorToBedRatio, d.noOfBeds, d.noOfConsultants, "
			+ "d.noOfDoctors, d.noOfFulltimeDoctors, d.noOfICUBeds, d.noOfICUDoctors, d.noOfICUNurses, "
			+ "d.noOfNurses, d.noOfSurgeons, d.nurseToBedICURatio,  d.nurseToBedRatio, d.websiteLink, "
			+ "e.accountNumber, e.accountType, e.beneficiaryName, e.branchName, e.exemptionEndDate,  "
			+ "e.exemptionStartDate, e.gstNo, e.ifscCode, e.legalName, e.lowerTDS, e.lowerTDSLimit,  "
			+ "e.panNo, e.providerRemarks, e.taxRegistrationNo, e.tinNo, e.tinUnderValueAddedTax, "
			+ "e.tradeName " + "from SendLink a "
			+ "left join GeneralInformation b on a.workflowNo = b.workflowNo "
			+ "left join ProviderInformation c on a.workflowNo = c.workflowNo "
			+ "left join InfraInformation d on a.workflowNo = d.workflowNo "
			+ "left join FinancialInformation e on a.workflowNo = e.workflowNo ")
	public List<Object[]> getAllWorkflowsReport();
	
	@Query("select a.workflowNo, a.address, a.email, a.mobileNumber, a.pincode, a.providerName, "
			+ "a.qc1Date ,a.queryRaisedDate,a.queryReason,a.rejectionDate,a.rejectionReason, a.rmAdId,"
			+ "a.rohiniId,a.submissionDate,a.workflowStatus, b.addressLine1, b.addressLine2, b.city, "
			+ "b.complianceFlag, b.country, b.district, b.latitude, b.licenceFlag, b.longitude, b.providerName, "
			+ "b.state, b.zipcode, c.hfrId, c.issuingAuthority, c.levelOfCare, c.ownershipType, "
			+ "c.placeOfRegistration, c.registrationExpiryDate, c.registrationNumber, c.rohiniCodeExpiryDate, "
			+ "c.rohiniId, c.specialization, c.subSpecialization, d.accreditationReceived, d.averageAdmissionTime, "
			+ "d.averageDischargeTime, d.averageStayForMedicalCases, d.averageStayForSurgicalCases, "
			+ "d.cSectionRate, d.doctorToBedICURatio, d.doctorToBedRatio, d.noOfBeds, d.noOfConsultants, "
			+ "d.noOfDoctors, d.noOfFulltimeDoctors, d.noOfICUBeds, d.noOfICUDoctors, d.noOfICUNurses, "
			+ "d.noOfNurses, d.noOfSurgeons, d.nurseToBedICURatio,  d.nurseToBedRatio, d.websiteLink, "
			+ "e.accountNumber, e.accountType, e.beneficiaryName, e.branchName, e.exemptionEndDate,  "
			+ "e.exemptionStartDate, e.gstNo, e.ifscCode, e.legalName, e.lowerTDS, e.lowerTDSLimit,  "
			+ "e.panNo, e.providerRemarks, e.taxRegistrationNo, e.tinNo, e.tinUnderValueAddedTax, "
			+ "e.tradeName " + "from SendLink a "
			+ "left join GeneralInformation b on a.workflowNo = b.workflowNo "
			+ "left join ProviderInformation c on a.workflowNo = c.workflowNo "
			+ "left join InfraInformation d on a.workflowNo = d.workflowNo "
			+ "left join FinancialInformation e on a.workflowNo = e.workflowNo "
			+ "where a.workflowNo =  :workflowNumber ")
	public List<Object[]> getReportByWorkflow(String workflowNumber);

	public List<SendLink> findByRmAdIdAndWorkflowStatus(String rmAdId, String workflowStatus);
	
	public List<SendLink> findByStateAndWorkflowStatus(String state, String workflowStatus);

	public List<SendLink> findByRmAdId(String rmAdId);

}