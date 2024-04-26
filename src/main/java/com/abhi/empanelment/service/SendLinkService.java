package com.abhi.empanelment.service;

import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.abhi.empanelment.pojo.WorkflowReport;
import com.abhi.empanelment.pojo.ZMDashboardResponse;
import com.abhi.empanelment.pojo.NATDashboardResponse;
import com.abhi.empanelment.repository.SendLinkRepository;

@Service
public class SendLinkService {

	@Autowired
	SendLinkRepository sendLinkRepository;

	private final Logger logger = LoggerFactory.getLogger(SendLinkService.class);
	

	public List<NATDashboardResponse> fetchNATDashboard() {
		logger.info("Inside SendLinkService class fetchNATDashboard() method");
		List<Object[]> result = null;
		ArrayList<NATDashboardResponse> returnList = new ArrayList<NATDashboardResponse>();
		result = sendLinkRepository.getNATDashBoard();
		try {
				result.stream().forEach((record) -> {
					NATDashboardResponse nATDashboardResponse = new NATDashboardResponse();
					nATDashboardResponse.setState((String) record[0]);
					nATDashboardResponse.setTotalReqRaisedCount((Long) record[1]);
					nATDashboardResponse.setPendingWithNATCount((Long) record[2]);
					nATDashboardResponse.setQc2PendingCaseCount((Long) record[3]);
					returnList.add(nATDashboardResponse);

				});
		} catch (Exception e) {
			logger.info("Error occured while fetching NAT dashboard");
			logger.info(e.getMessage());
		}

		return returnList;
	}
	
	public List<ZMDashboardResponse> fetchZMDashboard(String zmId) {
		logger.info("Inside SendLinkService class fetchZMDashboard() method");
		List<Object[]> result = null;
		ArrayList<ZMDashboardResponse> returnList = new ArrayList<ZMDashboardResponse>();
		result = sendLinkRepository.getZMDashBoard(zmId);
		try {
				result.stream().forEach((record) -> {
					ZMDashboardResponse zMDashboardResponse = new ZMDashboardResponse();
					zMDashboardResponse.setRmAdId((String) record[0]);
					zMDashboardResponse.setZmAdId((String) record[1]);
					zMDashboardResponse.setTotalReqRaisedCount((Long) record[2]);
					zMDashboardResponse.setPendingWithProviderCount((Long) record[3]);
					zMDashboardResponse.setPendingWithNATCount((Long) record[4]);
					zMDashboardResponse.setQueryCaseCount((Long) record[5]);
					zMDashboardResponse.setQc1ApprovedCaseCount((Long) record[6]);
					zMDashboardResponse.setMouStampingCaseCount((Long) record[7]);
					zMDashboardResponse.setRatelistUploadCaseCount((Long) record[8]);
					zMDashboardResponse.setPendingWithNATForQC2Count((Long) record[9]);
					zMDashboardResponse.setQc2QueryCaseCount((Long) record[10]);
					zMDashboardResponse.setQc2ApprovedCaseCount((Long) record[11]);
					zMDashboardResponse.setExpiredCaseCount((Long) record[12]);
					zMDashboardResponse.setRejectedCaseCount((Long) record[13]);
					returnList.add(zMDashboardResponse);

				});
		} catch (Exception e) {
			logger.info("Error occured while fetching NAT dashboard");
			logger.info(e.getMessage());
		}

		return returnList;
	}

	public List<WorkflowReport> fetchReportByWorkflow(String workflowNumber) {
		logger.info("Inside SendLinkService class fetchReportByWorkflow() method");
		List<Object[]> result = null;
		List<WorkflowReport> returnList = new ArrayList<WorkflowReport>();
		result = sendLinkRepository.getReportByWorkflow(workflowNumber);
		returnList = interateResultSet(result);
		return returnList;
	}

	public List<WorkflowReport> fetchAllWorkflowReport() {
		logger.info("Inside SendLinkService class fetchAllWorkflowReport() method");
		List<Object[]> result = null;
		List<WorkflowReport> returnList = new ArrayList<WorkflowReport>();
		result = sendLinkRepository.getAllWorkflowsReport();
		returnList = interateResultSet(result);
		return returnList;
	}

	public List<WorkflowReport> interateResultSet(List<Object[]> result) {

		logger.info("Inside SendLinkService class interateResultSet() method");
		ArrayList<WorkflowReport> returnList = new ArrayList<WorkflowReport>();

		try {
			result.stream().forEach((record) -> {
				WorkflowReport workflowReport = new WorkflowReport();
				logger.info("Interating report object for workflow no: " + (String) record[0]);
				workflowReport.setWorkflowNo((String) record[0]);
				workflowReport.setProviderAddress((String) record[1]);
				workflowReport.setProviderEmail((String) record[2]);
				workflowReport.setProviderMobileNo((String) record[3]);
				workflowReport.setProviderPincode((String) record[4]);
				workflowReport.setProviderName((String) record[5]);
				workflowReport.setQc1Date((Timestamp) record[6]);
				workflowReport.setQueryRaisedDate((String) record[7]);
				workflowReport.setQueryReason((String) record[8]);
				workflowReport.setRejectionDate((String) record[9]);
				workflowReport.setRejectionReason((String) record[10]);
				workflowReport.setRmAdId((String) record[11]);
				workflowReport.setProviderRohiniId((String) record[12]);
				workflowReport.setWorkflowSubmissionDate((Timestamp) record[13]);
				workflowReport.setWorkflowStatus((String) record[14]);
				workflowReport.setGiAddressLine1((String) record[15]);
				workflowReport.setGiAddressLine2((String) record[16]);
				workflowReport.setGicity((String) record[17]);
				if(record[18] != null)
				workflowReport.setGiComplianceFlag((boolean) record[18]);
				workflowReport.setGiCountry((String) record[19]);
				workflowReport.setGiDistrict((String) record[20]);
				workflowReport.setGiLatitude((String) record[21]);
				if(record[22] != null)
				workflowReport.setGiLicenceFlag((boolean) record[22]);
				workflowReport.setGiLongitude((String) record[23]);
				workflowReport.setGiProviderName((String) record[24]);
				workflowReport.setGiState((String) record[25]);
				workflowReport.setGiZipcode((String) record[26]);
				workflowReport.setPiHfrId((String) record[27]);
				workflowReport.setPiIssuingAuthority((String) record[28]);
				workflowReport.setPiLevelOfCare((String) record[29]);
				workflowReport.setPiOwnershipType((String) record[30]);
				workflowReport.setPiPlaceOfRegistration((String) record[31]);
				workflowReport.setPiRegistrationExpiryDate((String) record[32]);
				workflowReport.setPiRegistrationNumber((String) record[33]);
				workflowReport.setPiRohiniCodeExpiryDate((String) record[34]);
				workflowReport.setPiRohiniId((String) record[35]);
				workflowReport.setPiSpecialization((String) record[36]);
				workflowReport.setPiSubSpecialization((String) record[37]);
				workflowReport.setIiAccreditationReceived((String) record[38]);
				workflowReport.setIiAverageAdmissionTime((String) record[39]);
				workflowReport.setIiAverageDischargeTime((String) record[40]);
				workflowReport.setIiAverageStayForMedicalCases((String) record[41]);
				workflowReport.setIiAverageStayForSurgicalCases((String) record[42]);
				workflowReport.setIiCsectionRate((String) record[43]);
				workflowReport.setIiDoctorToBedIcuRatio((String) record[44]);
				workflowReport.setIiDoctorToBedRatio((String) record[45]);
				if(record[46] != null)
					workflowReport.setIiNoOfBeds((int) record[46]);
				if(record[47] != null)
					workflowReport.setIiNoOfConsultants((int) record[47]);
				if(record[48] != null)
					workflowReport.setIiNoOfDoctors((int) record[48]);
				if(record[49] != null)
					workflowReport.setIiNoOfFulltimeDoctors((int) record[49]);
				if(record[50] != null)
					workflowReport.setIiNoOfIcuBeds((int) record[50]);
				if(record[51] != null)
					workflowReport.setIiNoOfIcuDoctors((int) record[51]);
				if(record[52] != null)
					workflowReport.setIiNoOfIcuNurses((int) record[52]);
				if(record[53] != null)
					workflowReport.setIiNoOfNurses((int) record[53]);
				if(record[54] != null)
					workflowReport.setIiNoOfSurgeons((int) record[54]);
				workflowReport.setIiNurseToBedIcuRatio((String) record[55]);
				workflowReport.setIiNurseToBedRatio((String) record[56]);
				workflowReport.setIiWebsiteLink((String) record[57]);
				workflowReport.setFiAccountNumber((String) record[58]);
				workflowReport.setFiAccountType((String) record[59]);
				workflowReport.setFiBeneficiaryName((String) record[60]);
				workflowReport.setFiBranch_name((String) record[61]);
				workflowReport.setFiExemptionEndDate((String) record[62]);
				workflowReport.setFiExemptionStartDate((String) record[63]);
				workflowReport.setFiGstNo((String) record[64]);
				workflowReport.setFiIfscCode((String) record[65]);
				workflowReport.setFiLegalName((String) record[66]);
				workflowReport.setFiLowerTds((String) record[67]);
				workflowReport.setFiLowerTdsLimit((String) record[68]);
				workflowReport.setFiPanNo((String) record[69]);
				workflowReport.setFiProviderRemarks((String) record[70]);
				workflowReport.setFiTaxRegistrationNo((String) record[71]);
				workflowReport.setFiTinNo((String) record[72]);
				workflowReport.setFiTinUnderValueAddedTax((String) record[73]);
				workflowReport.setFiTradeName((String) record[74]);
				returnList.add(workflowReport);

			});
		} catch (Exception e) {
			logger.info("Error occured while interating resultset");
			logger.info(e.getMessage());
		}

		return returnList;

	}
}
