package com.abhi.empanelment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.abhi.empanelment.message.ResponseMessage;
import com.abhi.empanelment.model.AuditTrail;
import com.abhi.empanelment.model.SendLink;
import com.abhi.empanelment.model.User;
import com.abhi.empanelment.pojo.BitlyRequest;
import com.abhi.empanelment.pojo.CyberarkUserRequest;
import com.abhi.empanelment.pojo.SendOTPRequest;
import com.abhi.empanelment.pojo.ValidateOTPRequest;
import com.abhi.empanelment.repository.AuditTrailRepository;
import com.abhi.empanelment.repository.UserRepository;
import com.abhi.empanelment.service.AuditTrailService;
import com.abhi.empanelment.service.UtilityService;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/utility" })
public class UtilityController {
	Logger logger = LoggerFactory.getLogger(UtilityController.class);

	@Autowired
	UtilityService utilityService;
	@Autowired
	AuditTrailService auditTrailService;
	@Autowired
	UserRepository userRepository;

	@PostMapping({ "/sendBitly" })
	public Object getBitlyLink(@RequestBody BitlyRequest requestBody) throws IOException, InterruptedException {
		logger.info("inside UtilityController getBitlyLink() method");
		logger.info("workflow no received is: " + requestBody.getWorkflowNo());
		String getBitlyAPI = "https://webapi.abcap.co.in/ABCCap.svc/InsertShorturl";
		logger.info("calling bitly api");
		String shortURL = utilityService.callBitlyAPI(getBitlyAPI, requestBody.getLongUrl());
		logger.info("Retrived short url is: " + shortURL);
		auditTrailService.insertAuditTrail(requestBody.getWorkflowNo(), "Get Bitly", "Get Bitly", "Get Bitly", "SYSTEM",
				new java.util.Date(), 0);
		logger.info("Sending bitly link for workflow " + requestBody.getWorkflowNo());
		List<User> userList = userRepository.findByAdId(requestBody.getRmAdId());
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

//		String clickPSSAPI = "https://esblive.adityabirlahealth.com/ABHICL_ClickPSS/Service1.svc/click";
		logger.info("calling clickPSS api to trigger link on email for workflow: " + requestBody.getWorkflowNo());
		Object sendLink = utilityService.callClickPSSAPI("", requestBody.getWorkflowNo(), "A6054", "Empanelment Portal",
				"1", "", "", "", "1", requestBody.getHospitalMobileNo(), requestBody.getHospitalEmailId(),
				requestBody.getWorkflowNo(), shortURL, requestBody.getZone(), requestBody.getCity(),
				requestBody.getRmAdId(), requestBody.getRmMobileNo(), requestBody.getRmEmailId(), "", "", "",
				rmEmailId + ";" + zmEmailId);
		auditTrailService.insertAuditTrail(requestBody.getWorkflowNo(), "Send Bitly", "Send Bitly", "Send Bitly",
				"SYSTEM", new java.util.Date(), 0);
		return sendLink;
	}

//	@PostMapping({ "/sendOTP" })
//	public Object sendOTP(@RequestBody SendOTPRequest requestBody) throws IOException, InterruptedException {
//		logger.info("inside UtilityController sendOTP() method");
//		logger.info("Sending OTP for workflow: " + requestBody.getWorkflowNo());
//		Object snedOTPResponseObject = null;
//		snedOTPResponseObject = utilityService.callAISOTPService(requestBody);
//		auditTrailService.insertAuditTrail(requestBody.getWorkflowNo(), "Send OTP", "Send OTP", "Send OTP", "SYSTEM",
//				new java.util.Date(), 0);
//		return snedOTPResponseObject;
//	}
	
	@PostMapping({ "/sendOTP" })
	public Object sendOTP(@RequestBody String inputText) throws IOException, InterruptedException {
		logger.info("inside UtilityController sendOTP() method");
		
		return utilityService.callAISOTPService(inputText);
		
	}

//	@PostMapping({ "/validateOTP" })
//	public Object validateOTP(@RequestBody ValidateOTPRequest validateOTPRequest)
//			throws IOException, InterruptedException {
//		logger.info("inside UtilityController validateOTP() method");
//		Object validateOTPResponseObject = null;
//		validateOTPResponseObject = utilityService.callAISValidateOTPService(validateOTPRequest);
//		auditTrailService.insertAuditTrail(validateOTPRequest.getWorkflowNo(), "validate OTP", "validate OTP",
//				"validate OTP", "SYSTEM", new java.util.Date(), 0);
//		return validateOTPResponseObject;
//	}
	
	@PostMapping({ "/validateOTP" })
	public Object validateOTP(@RequestBody String inputText)
			throws IOException, InterruptedException {
		logger.info("inside UtilityController validateOTP() method");
		return utilityService.callAISValidateOTPService(inputText);
	}

	@GetMapping({ "/getAdressFromGoogle/{address}" })
	public Object getAddressFromGoogle(@PathVariable(value = "address") String addressToSearch)
			throws IOException, InterruptedException {
		logger.info("inside UtilityController getAdressFromGoogle() method");
		return utilityService.callGoogleAddressAPI(addressToSearch);
	}

	@GetMapping({ "/getHypervergeToken/{workflowNo}" })
	public Object getHypervergeToken(@PathVariable(value = "workflowNo") String workflowNumber)
			throws IOException, InterruptedException {
		logger.info("inside UtilityController getHypervergeToken() method");
		return utilityService.callHypervergeTokenAPI(workflowNumber);
	}

	@PostMapping({ "/getCyberarkUser" })
	public Object getCyberarkUser(@RequestBody CyberarkUserRequest requestBody)
			throws IOException, InterruptedException {
		logger.info("inside UtilityController getCyberarkUser() method");
		return utilityService.callCyberarkAPI(requestBody.getTokenId());
	}

}