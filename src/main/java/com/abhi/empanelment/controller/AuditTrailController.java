package com.abhi.empanelment.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;

import com.abhi.empanelment.model.AuditTrail;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.abhi.empanelment.repository.AuditTrailRepository;
import com.abhi.empanelment.service.AuditTrailService;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/auditTrail" })
public class AuditTrailController
{
	private final Logger logger = LoggerFactory.getLogger(AuditTrailController.class);
	
    @Autowired
    AuditTrailRepository auditTrailRepository;
    
    @Autowired
    AuditTrailService auditTrailService;
    
    @GetMapping({ "/getAll" })
    public List<AuditTrail> getAllRecords() {
    	logger.info("inside AuditTrailController getAllRecords() method");
        return auditTrailRepository.findAll();
    }
    
    @PostMapping({ "/saveAuditTrail" })
    public AuditTrail createRecord(@Valid @RequestBody AuditTrail auditTrail) {
    	logger.info("inside AuditTrailController createRecord() method");
        return auditTrailRepository.save(auditTrail);
    }
    
    @GetMapping({ "/getAuditByWorkflowNo/{id}" })
    public List<AuditTrail> getRecordByWorkflowNo(@PathVariable("id") final String workflowNo) {
    	logger.info("inside AuditTrailController getRecordByWorkflowNo() method");
        return auditTrailService.findByWorkflowNo(workflowNo);
    }
}