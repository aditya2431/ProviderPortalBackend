package com.abhi.empanelment.controller;

import com.abhi.empanelment.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import com.abhi.empanelment.model.InfraInformation;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.abhi.empanelment.repository.InfraInfoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/infraInfo" })
public class InfraInfoController {
	private final Logger logger = LoggerFactory.getLogger(InfraInfoController.class);

	@Autowired
	InfraInfoRepository infraInfoRepository;

	@GetMapping({ "/getAll" })
	public List<InfraInformation> getAllRecords() {
		logger.info("inside InfraInfoController getAllRecords() method");
		return infraInfoRepository.findAll();
	}

	@PostMapping({ "/saveInfraInfo" })
	public InfraInformation createRecord(@Valid @RequestBody InfraInformation infraInfo) {
		logger.info("inside InfraInfoController createRecord() method");
		return infraInfoRepository.save(infraInfo);
	}

	@GetMapping({ "/getByWorkflowNo/{id}" })
	public InfraInformation getRecordByWorkflowNo(@PathVariable("id") final String workflowNo) {
		logger.info("inside InfraInfoController getRecordByWorkflowNo() method");
		return infraInfoRepository.findById(workflowNo)
				.orElseThrow(() -> new ResourceNotFoundException("workflowNo", "id", workflowNo));
	}
}