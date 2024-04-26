package com.abhi.empanelment.schedular;

import java.time.format.DateTimeFormatter;

import org.springframework.transaction.annotation.Transactional;

import com.abhi.empanelment.repository.SendLinkRepository;
import com.abhi.empanelment.repository.UserRepository;
import com.abhi.empanelment.service.AuditTrailService;
import com.abhi.empanelment.service.UtilityService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;

@Component
public class ScheduledTaskService {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");
	private final Logger logger = LoggerFactory.getLogger(ScheduledTaskService.class);
	
	@Autowired
	SendLinkRepository sendLinkRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AuditTrailService auditTrailService;
	
	@Autowired
	UtilityService utilityService;

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	@Scheduled(fixedRate=60*60*5000)
//	@Scheduled(cron = "0 0 7 * * *")
	public void execute() throws InterruptedException {
		// some logic that will be executed on a schedule
		
		logger.info("Starting application scheduler task");
		
		LinkExpiryReminder3Days reminder = new LinkExpiryReminder3Days(sendLinkRepository, userRepository, auditTrailService, utilityService);
		NatApprovalReminder natApprovalReminder =  new NatApprovalReminder(sendLinkRepository, userRepository, auditTrailService, utilityService);
		QueryReminder queryReminder =  new QueryReminder(sendLinkRepository, userRepository, auditTrailService, utilityService);
		MOUReminder mouReminder =  new MOUReminder(sendLinkRepository, userRepository, auditTrailService, utilityService);

		
		reminder.sendReminder(3, "A6060", "Init");
		Thread.sleep(3000);
		reminder.sendReminder(3, "A6061", "Init");
		Thread.sleep(3000);
		reminder.sendReminder(6, "A6062", "Init");
		Thread.sleep(3000);
		reminder.sendReminder(6, "A6063", "Init");
		Thread.sleep(3000);
		reminder.sendReminder(9, "A6064","Init");
		Thread.sleep(3000);
		reminder.sendReminder(60, "A6066","Init");
		
//		natApprovalReminder.sendReminder(3, "Submit");
//		Thread.sleep(3000);
//		natApprovalReminder.sendReminder(5, "Submit");
//		Thread.sleep(3000);
		
		queryReminder.sendReminder(3, "A6057", "Query");
		Thread.sleep(3000);
		queryReminder.sendReminder(5, "A6058", "Query");
		Thread.sleep(3000); 
		queryReminder.sendReminder(10, "A6059", "Query");
		Thread.sleep(3000); 
		
		mouReminder.sendReminder(2, "A6087", "MOU Generated");
		Thread.sleep(3000);
		mouReminder.sendReminder(4, "A6088", "MOU Generated");
		Thread.sleep(3000); 

	}
}