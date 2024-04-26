package com.abhi.empanelment.schedular;

import java.util.Date;
import java.util.List;
import java.text.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.abhi.empanelment.utility.DateConverter;
import com.abhi.empanelment.model.SendLink;
import com.abhi.empanelment.model.User;
import com.abhi.empanelment.repository.SendLinkRepository;
import com.abhi.empanelment.repository.UserRepository;
import com.abhi.empanelment.service.AuditTrailService;
import com.abhi.empanelment.service.UtilityService;

public class LinkExpiryReminder3Days {

	private final Logger logger = LoggerFactory.getLogger(LinkExpiryReminder3Days.class);

	private SendLinkRepository sendLinkRepository;
	private UserRepository userRepository;
	private AuditTrailService auditTrailService;
	private UtilityService utilityService;

	LinkExpiryReminder3Days(SendLinkRepository sendLinkRepository, UserRepository userRepository,
			AuditTrailService auditTrailService, UtilityService UtilityService) {
		this.sendLinkRepository = sendLinkRepository;
		this.userRepository = userRepository;
		this.auditTrailService = auditTrailService;
		this.utilityService = UtilityService;
	}

	public void sendReminder(int days, String alertId, String workflowStatus) throws InterruptedException {

		logger.info("inside LinkExpiryReminder3Days sendReminder() method");

		DateConverter dateConverter = new DateConverter();

		String convertedDate = dateConverter.DateSub(days);
		logger.info("fetching workflows for date diff of: " + days + " days");

		String zone = null;
		String location = null;
		String rmName = null;
		String rmMobileNo = null;
		String rmEmailId = null;
		String zmEmailId = null;

		List<SendLink> workflowList = sendLinkRepository.findBySubmissionDate(convertedDate, workflowStatus);
		logger.info(
				"Workflow count from link expiry reminder for date diff of :" + days + " is : " + workflowList.size());
		if (!workflowList.isEmpty()) {
			for (SendLink sendLink : workflowList) {
				logger.info(sendLink.getWorkflowNo());
//				List<User> userList = userRepository.findByAdId(sendLink.getRmAdId());
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
				String todayAsString = df.format(sendLink.getSubmissionDate());
				
				utilityService.callClickPSSAPI("", sendLink.getWorkflowNo(), alertId, "Hospital Empanelment", "1", "",
						"", "", "1", sendLink.getMobileNumber(), sendLink.getEmail(), sendLink.getWorkflowNo(),
						todayAsString, zone, location, rmName, rmMobileNo, rmEmailId, "", "",
						"", rmEmailId + ","+ zmEmailId);

				if(days == 9) {
					sendLink.setWorkflowStatus("Expire");
					SendLink updatedSendLink = sendLinkRepository.save(sendLink);
					logger.info("Workflow status successfully updated after sending 9 days link expiry reminder for workflow: "
							+ updatedSendLink.getWorkflowNo());
				}
				if(days == 60) {
					sendLink.setWorkflowStatus("Closed");
					SendLink updatedSendLink = sendLinkRepository.save(sendLink);
					logger.info("Workflow status successfully updated after sending 60 days link expiry reminder for workflow: "
							+ updatedSendLink.getWorkflowNo());
				}
				
				auditTrailService.insertAuditTrail(sendLink.getWorkflowNo(),
						"Link expiry reminder for " + days + " days", "Link expiry reminder", "Link expiry reminder",
						"SYSTEM", new java.util.Date(), 0);
				Thread.sleep(3000);
			}
		}
	}

}
