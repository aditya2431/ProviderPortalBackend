package com.abhi.empanelment.schedular;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abhi.empanelment.utility.DateConverter;
import com.abhi.empanelment.model.SendLink;
import com.abhi.empanelment.model.User;
import com.abhi.empanelment.repository.SendLinkRepository;
import com.abhi.empanelment.repository.UserRepository;
import com.abhi.empanelment.service.AuditTrailService;
import com.abhi.empanelment.service.UtilityService;

public class MOUReminder {

	private final Logger logger = LoggerFactory.getLogger(MOUReminder.class);

	private SendLinkRepository sendLinkRepository;
	private UserRepository userRepository;
	private AuditTrailService auditTrailService;
	private UtilityService utilityService;

	MOUReminder(SendLinkRepository sendLinkRepository, UserRepository userRepository,
			AuditTrailService auditTrailService, UtilityService utilityService) {
		this.sendLinkRepository = sendLinkRepository;
		this.userRepository = userRepository;
		this.auditTrailService = auditTrailService;
		this.utilityService = utilityService;
	}

	public void sendReminder(int days, String alertId, String workflowStatus) throws InterruptedException {

		logger.info("inside MOUReminder sendReminder() method");

		DateConverter dateConverter = new DateConverter();

		String convertedDate = dateConverter.DateSub(days);
		logger.info("fetching MOU workflows for date diff of: " + days + " days");

		String zone = null;
		String location = null;
		String rmName = null;
		String rmMobileNo = null;
		String rmEmailId = null;
		String zmEmailId = null;

		List<SendLink> workflowList = sendLinkRepository.findByMouGenerationDate(convertedDate, workflowStatus);
		logger.info("Workflow count from query reminder for date diff of :" + days + " is : " + workflowList.size());
		if (!workflowList.isEmpty()) {
			for (SendLink sendLink : workflowList) {
				logger.info(sendLink.getWorkflowNo());
//				List<User> userlist = userRepository.findByAdId(sendLink.getRmAdId());
				List<User> userList = userRepository.findByAdIdAndLocation(sendLink.getRmAdId(), sendLink.getState());
				for (User user : userList) {
					zone = user.getZone();
					location = user.getLocation();
					rmName = user.getRmName();
					rmMobileNo = user.getRmMobileNumber();
					rmEmailId = user.getRmEmailId();
					zmEmailId = user.getZmEmailId();
				}

				String pattern = "dd/MM/yyyy HH:mm:ss";
				DateFormat df = new SimpleDateFormat(pattern);
				String todayAsString = df.format(sendLink.getMouGenerationDate());

				utilityService.callClickPSSAPI("", sendLink.getWorkflowNo(), alertId, "Hospital Empanelment", "1", "",
						"", "", "1", sendLink.getMobileNumber(), sendLink.getEmail(), sendLink.getWorkflowNo(),
						todayAsString, "https://abcap.co.in/2yKk7dZ", zone, location, rmName, rmMobileNo, rmEmailId, "",
						"", rmEmailId + "," + zmEmailId);

				auditTrailService.insertAuditTrail(sendLink.getWorkflowNo(), "MOU reminder for " + days + " days",
						"MOU reminder", "MOU reminder", "SYSTEM", new java.util.Date(), 0);
				Thread.sleep(3000);
			}
		}
	}

}
