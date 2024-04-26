package com.abhi.empanelment.schedular;

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

public class NatApprovalReminder {

	private final Logger logger = LoggerFactory.getLogger(NatApprovalReminder.class);

	private SendLinkRepository sendLinkRepository;
	private UserRepository userRepository;
	private AuditTrailService auditTrailService;
	private UtilityService utilityService;

	NatApprovalReminder(SendLinkRepository sendLinkRepository, UserRepository userRepository,
			AuditTrailService auditTrailService, UtilityService utilityService) {
		this.sendLinkRepository = sendLinkRepository;
		this.userRepository = userRepository;
		this.auditTrailService = auditTrailService;
		this.utilityService = utilityService;
	}

	public void sendReminder(int days, String workflowStatus) throws InterruptedException {

		logger.info("inside NatApprovalReminder sendReminder() method");

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
				utilityService.callClickPSSAPI("", sendLink.getWorkflowNo(), "A6060", "Hospital Empanelment", "1", "",
						"", "", "1", sendLink.getMobileNumber(), sendLink.getEmail(), sendLink.getWorkflowNo(), "",
						zone, location, rmName, rmMobileNo, rmEmailId, "", "", "", rmEmailId +","+ zmEmailId);

				auditTrailService.insertAuditTrail(sendLink.getWorkflowNo(),
						"Link expiry reminder for " + days + " days", "Link expiry reminder", "Link expiry reminder",
						"SYSTEM", new java.util.Date(), 0);
				Thread.sleep(3000);
			}
		}
	}

}
