package com.abhi.empanelment.controller;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.empanelment.model.SendLink;
import com.abhi.empanelment.model.User;
import com.abhi.empanelment.pojo.ClickPSSMOUResponse;
import com.abhi.empanelment.pojo.NATDashboardResponse;
import com.abhi.empanelment.pojo.WorkflowReport;
import com.abhi.empanelment.pojo.ZMDashboardResponse;
import com.abhi.empanelment.repository.SendLinkRepository;
import com.abhi.empanelment.repository.UserRepository;
import com.abhi.empanelment.service.AuditTrailService;
import com.abhi.empanelment.service.SendLinkService;
import com.abhi.empanelment.service.UtilityService;
import com.abhi.empanelment.exception.ResourceNotFoundException;
import com.abhi.empanelment.message.ResponseMessage;

@RestController
@RequestMapping({ "/api/sendLink" })
public class SendLinkController {

	private static final Logger logger = LogManager.getLogger(SendLinkController.class);

	@Autowired
	SendLinkRepository sendLinkRepository;

	@Autowired
	UtilityService utilityService;

	@Autowired
	AuditTrailService auditTrailService;

	@Autowired
	SendLinkService sendLinkService;

	@Autowired
	UserRepository userRepository;

	@GetMapping({ "/getAll" })
	public List<SendLink> getAllRecords() {
		logger.info("Inside sendLinkController getAllRecords() method");
		return sendLinkRepository.findAll();
	}

	@PostMapping({ "/triggerLink" })
	public Object createRecord(@Valid @RequestBody SendLink sendLink) {
		List<SendLink> existingRecord = null;
		logger.info("Inside sendLinkController createRecord() method");
		existingRecord = sendLinkRepository.findByEmail(sendLink.getEmail());
		if (existingRecord == null || existingRecord.isEmpty()) {
			SendLink sendlinkResp = sendLinkRepository.save(sendLink);
			auditTrailService.insertAuditTrail(sendlinkResp.getWorkflowNo(), "Init", "Hospital Queue", "Trigger link",
					sendlinkResp.getRmAdId(), new java.util.Date(), 0);
			return ResponseEntity.status(HttpStatus.OK).body(sendlinkResp);
		} else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ResponseMessage("Data already exist for this workflow"));
		}
	}

	@PutMapping("/updateStatus/{id}")
	public ResponseEntity<SendLink> updateWorfkwloStatus(@PathVariable(value = "id") String workflowNo,
			@RequestBody SendLink sendlinkDetails) {
		logger.info("Inside sendLinkController updateWorfkwloStatus() method");
		SendLink sendLink = sendLinkRepository.findById(workflowNo)
				.orElseThrow(() -> new ResourceNotFoundException("sendLink", "id", workflowNo));
		sendLink.setWorkflowNo(sendlinkDetails.getWorkflowNo());
		sendLink.setWorkflowStatus(sendlinkDetails.getWorkflowStatus());
		sendLink.setRmAdId(sendlinkDetails.getRmAdId());
		sendLink.setProviderName(sendlinkDetails.getProviderName());
		sendLink.setEmail(sendlinkDetails.getEmail());
		sendLink.setMobileNumber(sendlinkDetails.getMobileNumber());
		sendLink.setAddress(sendlinkDetails.getAddress());
		sendLink.setPincode(sendlinkDetails.getPincode());
		sendLink.setRohiniId(sendlinkDetails.getRohiniId());
		sendLink.setQc1Date(sendlinkDetails.getQc1Date());
		sendLink.setQueryRaisedDate(sendlinkDetails.getQueryRaisedDate());
		sendLink.setRejectionDate(sendlinkDetails.getRejectionDate());
		sendLink.setQueryReason(sendlinkDetails.getQueryReason());
		sendLink.setRejectionReason(sendlinkDetails.getRejectionReason());
		sendLink.setContactPerson(sendlinkDetails.getContactPerson());
		SendLink updatedSendLink = sendLinkRepository.save(sendLink);
		logger.info("printing workflow status " + sendlinkDetails.getWorkflowStatus());
		List<User> userList = userRepository.findByAdIdAndLocation(updatedSendLink.getRmAdId(),
				updatedSendLink.getState());
		String zone = null;
		String location = null;
		String rmName = null;
		String rmMobileNo = null;
		String rmEmailId = null;
		String zmEmailId = null;
		ResponseEntity<ResponseMessage> response = null;

		for (User user : userList) {
			zone = user.getZone();
			location = user.getLocation();
			rmName = user.getRmName();
			rmMobileNo = user.getRmMobileNumber();
			rmEmailId = user.getRmEmailId();
			zmEmailId = user.getZmEmailId();
		}
		if (sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("QC1")) {
			try {
				// Inserting audit trail for QC1 action
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "QC1", "RM Queue", "QC1 Approval",
						updatedSendLink.getRmAdId(), new java.util.Date(), 0);
				logger.info("Sending approval email for workflow: " + updatedSendLink.getWorkflowNo());
				// calling ClickPSS API
				response = utilityService.callClickPSSAPI("", updatedSendLink.getWorkflowNo(), "A6055",
						"Hospital Empanelment", "1", "", "", "", "1", updatedSendLink.getMobileNumber(),
						updatedSendLink.getEmail(), updatedSendLink.getWorkflowNo(), "", zone, location, rmName,
						rmMobileNo, rmEmailId, "", "", "", rmEmailId + "," + zmEmailId);
				response = utilityService.callClickPSSAPI("", updatedSendLink.getWorkflowNo(), "A6094",
						"Hospital Empanelment", "1", "", "", "", "1", rmMobileNo, rmEmailId,
						updatedSendLink.getWorkflowNo(), "", zone, location, rmName, rmMobileNo, rmEmailId, "", "", "",
						zmEmailId);
				// inserting audit trail record
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "QC1 Approval Email",
						"QC1 Approval Email", "QC1 Approval Email", "SYSTEM", new java.util.Date(), 0);
			} catch (Exception e) {
				logger.info(
						"Error occured while sending approval email for workflow: " + updatedSendLink.getWorkflowNo());
			}
		}

		if (sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("Submit")) {
			try {
				// Inserting audit trail for QC1 action
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "Submit", "NAT Queue",
						"Workflow Submmited", updatedSendLink.getEmail(), new java.util.Date(), 0);
			} catch (Exception e) {
				logger.info(
						"Error occured while inserting audit trail for workflow: " + updatedSendLink.getWorkflowNo());
			}
		}

		if (sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("Query")) {
			try {
				String pattern = "dd/MM/yyyy HH:mm:ss";
				DateFormat df = new SimpleDateFormat(pattern);
				String todayAsString = df.format(updatedSendLink.getQueryRaisedDate());
				// Inserting audit trail for query action
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "QC1 - Query", "Hospital Queue",
						"QC1 Query", updatedSendLink.getRmAdId(), new java.util.Date(), 0);
				logger.info("Sending query email for workflow: " + updatedSendLink.getWorkflowNo());
				// calling clickPSS API
				response = utilityService.callClickPSSAPI("", updatedSendLink.getWorkflowNo(), "A6056",
						"Hospital Empanelment", "1", "", "", "", "1", updatedSendLink.getMobileNumber(),
						updatedSendLink.getEmail(), updatedSendLink.getWorkflowNo(), todayAsString, zone, location,
						rmName, rmMobileNo, rmEmailId, "", updatedSendLink.getQueryReason(), "",
						rmEmailId + "," + zmEmailId);
				// inserting audit trail record
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "QC1 Query Email",
						"QC1 Query Email", "QC1 Query Email", "SYSTEM", new java.util.Date(), 0);
			} catch (Exception e) {
				logger.info("Error occured while sending query email for workflow: " + updatedSendLink.getWorkflowNo());
			}
		}

		if (sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("QC2 Query")) {
			try {
				String pattern = "dd/MM/yyyy HH:mm:ss";
				DateFormat df = new SimpleDateFormat(pattern);
				String todayAsString = df.format(updatedSendLink.getQueryRaisedDate());
				logger.info("Sending query email for workflow: " + updatedSendLink.getWorkflowNo());
				// calling clickPSS API
				response = utilityService.callClickPSSAPI("", updatedSendLink.getWorkflowNo(), "A6056",
						"Hospital Empanelment", "1", "", "", "", "1", rmMobileNo,
						rmEmailId, updatedSendLink.getWorkflowNo(), todayAsString, zone, location,
						rmName, rmMobileNo, rmEmailId, "", updatedSendLink.getQueryReason(), "", zmEmailId);
				 //inserting audit trail record
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "QC2 Query",
						"RM Queue", "QC2 Query", updatedSendLink.getRmAdId(), new java.util.Date(), 0);
			} catch (Exception e) {
				logger.info("Error occured while sending query email for workflow: " + updatedSendLink.getWorkflowNo());
			}
		}

		if (sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("Reject")) {
			try {
				String pattern = "dd/MM/yyyy HH:mm:ss";
				DateFormat df = new SimpleDateFormat(pattern);
				String todayAsString = df.format(updatedSendLink.getSubmissionDate());

				// Inserting audit trail for query action
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "QC1 - Reject",
						"Workflow Completed", "QC1 Reject", updatedSendLink.getRmAdId(), new java.util.Date(), 0);
				logger.info("Sending rejection email for workflow: " + updatedSendLink.getWorkflowNo());
				// calling clickPSS API
				response = utilityService.callClickPSSAPI("", updatedSendLink.getWorkflowNo(), "A6065",
						"Hospital Empanelment", "1", "", "", "", "1", updatedSendLink.getMobileNumber(),
						updatedSendLink.getEmail(), updatedSendLink.getWorkflowNo(), todayAsString, zone, location,
						rmName, rmMobileNo, rmEmailId, updatedSendLink.getRejectionReason(), "", "",
						rmEmailId + "," + zmEmailId);
				// inserting audit trail record
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "QC1 Rejection Email",
						"QC1 Rejection Email", "QC1 Rejection Email", "SYSTEM", new java.util.Date(), 0);
			} catch (Exception e) {
				logger.info(
						"Error occured while sending rejection email for workflow: " + updatedSendLink.getWorkflowNo());
			}
		}

		if (sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("QC2")) {
			try {

				logger.info("Sending QC2 approval email for workflow: " + updatedSendLink.getWorkflowNo());
				response = utilityService.callClickPSSAPI("", updatedSendLink.getWorkflowNo(), "A6093",
						"Hospital Empanelment", "1", "", "", "", "1", rmMobileNo, rmEmailId,
						updatedSendLink.getWorkflowNo(), "", zone, location, rmName, rmMobileNo, rmEmailId, "", "", "",
						zmEmailId);
				// Inserting audit trail for query action
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "QC2 - Approval",
						"Workflow Completed", "QC2 Approval", updatedSendLink.getRmAdId(), new java.util.Date(), 0);
			} catch (Exception e) {
				logger.info(
						"Error occured while sending rejection email for workflow: " + updatedSendLink.getWorkflowNo());
			}
		}
		
		if (sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("Eligible For QC2")) {
			try {
				logger.info("Sending QC2 eligibilty email for workflow: " + updatedSendLink.getWorkflowNo());
				response = utilityService.callClickPSSAPI("", updatedSendLink.getWorkflowNo(), "A6095",
						"Hospital Empanelment", "1", "", "", "", "1", rmMobileNo, rmEmailId,
						updatedSendLink.getWorkflowNo(), "", zone, location, rmName, rmMobileNo, rmEmailId, "", "", "",
						zmEmailId);
				// Inserting audit trail for query action
				auditTrailService.insertAuditTrail(updatedSendLink.getWorkflowNo(), "Eligible For QC2",
						"Eligible For QC2", "Eligible For QC2", "SYSTEM", new java.util.Date(), 0);
			} catch (Exception e) {
				logger.info(
						"Error occured while sending rejection email for workflow: " + updatedSendLink.getWorkflowNo());
			}
		}

		if (sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("QC1")
				|| sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("Query")
				|| sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("Reject")
				|| sendlinkDetails.getWorkflowStatus().equalsIgnoreCase("QC2") && response != null) {
			if (response.getStatusCode() == HttpStatus.OK) {
				return ResponseEntity.ok(updatedSendLink);
			} else {
				logger.info("Error occured while sending email for workflow :" + updatedSendLink.getWorkflowNo());
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(updatedSendLink);
			}
		}

		else {
			return ResponseEntity.ok(updatedSendLink);
		}

	}

	@PostMapping({ "/getByParams" })
	public List<SendLink> getByParams(@RequestBody SendLink sendLink) {
		logger.info("Inside sendLinkController getByParams() method");
		logger.info("rohini id in controller: " + sendLink.getRohiniId());

		return sendLinkRepository.findByCustomParams(sendLink.getWorkflowNo(), sendLink.getRohiniId(),
				sendLink.getProviderName(), sendLink.getPincode());
	}

	@GetMapping({ "/getBySubmissionDate/{date}" })
	public List<SendLink> findBySubmissionDate(@PathVariable(value = "date") String submissionDate) {
		logger.info("Inside sendLinkController getBySubmissionDate() method");
		return sendLinkRepository.findBySubmissionDate(submissionDate, "Init");
	}

	@GetMapping({ "/fetchRMDashBoard/{rmAdId}" })
	public List<Object[]> fetchRMDashBoard(@PathVariable(value = "rmAdId") String rmAdId) {
		logger.info("Inside sendLinkController fetchRMDashBoard() method");
		return sendLinkRepository.getRMDashBoard(rmAdId);
	}

	@GetMapping({ "/fetchZMDashBoard/{zmAdId}" })
	public List<ZMDashboardResponse> fetchZMDashBoard(@PathVariable(value = "zmAdId") String zm) {
		logger.info("Inside sendLinkController fetchZMDashBoard() method");
		return sendLinkService.fetchZMDashboard(zm);
	}

	@GetMapping({ "/fetchNATDashBoard" })
	public List<NATDashboardResponse> fetchNATDashBoard() {
		logger.info("Inside sendLinkController fetchNATDashBoard() method");
		return sendLinkService.fetchNATDashboard();
	}

	@GetMapping({ "/fetchByRmAndStatus/{rmAdId}/{workflowStatus}" })
	public List<SendLink> fetchByRmAndStatus(@PathVariable(value = "rmAdId") String rmAdId,
			@PathVariable(value = "workflowStatus") String workflowStatus) {
		return sendLinkRepository.findByRmAdIdAndWorkflowStatus(rmAdId, workflowStatus);

	}

	@GetMapping({ "/fetchByStateAndStatus/{state}/{workflowStatus}" })
	public List<SendLink> fetchByStateAndStatus(@PathVariable(value = "state") String stateName,
			@PathVariable(value = "workflowStatus") String workflowStatus) {
		return sendLinkRepository.findByStateAndWorkflowStatus(stateName, workflowStatus);

	}

	@GetMapping({ "/fetchByRm/{rmAdId}" })
	public List<SendLink> fetchByRm(@PathVariable(value = "rmAdId") String rmAdId) {
		return sendLinkRepository.findByRmAdId(rmAdId);

	}

	@GetMapping({ "/fetchAllWorkflowsReport" })
	public List<WorkflowReport> fetchAllWorkflowsReport() {
		logger.info("Inside sendLinkController fetchAllWorkflowsReport() method");
		return sendLinkService.fetchAllWorkflowReport();
	}

	@GetMapping({ "/fetchReportByWorkflow/{workflowNo}" })
	public List<WorkflowReport> fetchReportByWorkflow(@PathVariable(value = "workflowNo") String workflowNumber) {
		logger.info("Inside sendLinkController fetchReportByWorkflow() method");
		return sendLinkService.fetchReportByWorkflow(workflowNumber);
	}

	@GetMapping({ "/getByEmail/{emailId}" })
	public List<SendLink> fetchByEmail(@PathVariable(value = "emailId") String email) {
		logger.info("Inside sendLinkController fetchByEmail() method");
		return sendLinkRepository.findByEmail(email);
	}

	@GetMapping({ "/getByState/{state}" })
	public List<SendLink> fetchByState(@PathVariable(value = "state") String stateValue) {
		logger.info("Inside sendLinkController fetchByState() method");
		return sendLinkRepository.findByState(stateValue);
	}

}
