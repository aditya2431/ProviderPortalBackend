package com.abhi.empanelment.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.empanelment.repository.AuditTrailRepository;
import com.abhi.empanelment.controller.GeneralInfoController;
import com.abhi.empanelment.model.AuditTrail;

@Service
public class AuditTrailService {

	private final Logger logger = LoggerFactory.getLogger(AuditTrailService.class);

	@Autowired
	AuditTrailRepository auditTrailRepository;

	public void insertAuditTrail(String workflowNo, String taskName, String queueName, String action, String actionedBy,
			Date actionedDate, int sla) {

		try {
			logger.info("Inside AuditTrailService class insertAuditTrail() method");
			AuditTrail auditTrail = new AuditTrail();
			auditTrail.setWorkflowNo(workflowNo);
			auditTrail.setTaskName(taskName);
			auditTrail.setQueueName(queueName);
			auditTrail.setAction(action);
			auditTrail.setActionedBy(actionedBy);
			auditTrail.setActionedDate(actionedDate);
			auditTrail.setSla(sla);

			logger.info("inserting audit trail record for workflow: " + workflowNo);
			auditTrailRepository.save(auditTrail);
		} catch (Exception e) {
			logger.info("Error occured while inserting audit trail record for workflow: " + workflowNo);
			logger.info(e.getMessage());
		}
	}

	public List<AuditTrail> findByWorkflowNo(String workflowNo) {
		logger.info("Inside AuditTrailService class findByWorkflowNo() method");
		logger.info("Fetching audit trail records for workflow: "+workflowNo);
		List<Object[]> result = null;
		ArrayList<AuditTrail> returnList = new ArrayList<AuditTrail>();
		result = auditTrailRepository.findByWorkflowNo(workflowNo);
		try {
			result.stream().forEach((record) -> {
				AuditTrail auditTrail = new AuditTrail();
				auditTrail.setId((Integer) record[0]);
				auditTrail.setAction((String) record[1]);
				auditTrail.setActionedBy((String) record[2]);
				auditTrail.setActionedDate((Date) record[3]);
				auditTrail.setQueueName((String) record[4]);
				auditTrail.setTaskName((String) record[5]);
				auditTrail.setWorkflowNo((String) record[6]);
				auditTrail.setSla((Integer) record[7]);
				returnList.add(auditTrail);

			});
		} catch (Exception e) {
			logger.info(e.getMessage());
		}

		return returnList;
	}

}
