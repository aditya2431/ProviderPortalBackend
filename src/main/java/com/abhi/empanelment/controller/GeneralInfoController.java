package com.abhi.empanelment.controller;

import com.abhi.empanelment.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import com.abhi.empanelment.model.GeneralInformation;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.abhi.empanelment.repository.GeneralInfoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/generalInfo" })
public class GeneralInfoController {
	private final Logger logger = LoggerFactory.getLogger(GeneralInfoController.class);

	@Autowired
	GeneralInfoRepository generalInfoRepository;

	@GetMapping({ "/getAll" })
	public List<GeneralInformation> getAllRecords() {
		logger.info("inside GeneralInfoController getAllRecords() method");
		return generalInfoRepository.findAll();
	}

	@PostMapping({ "/saveGeneralInfo" })
	public GeneralInformation createRecord(@Valid @RequestBody GeneralInformation generalInfo) {
		logger.info("inside GeneralInfoController createRecord() method");
		return generalInfoRepository.save(generalInfo);
	}

	@GetMapping({ "/getByWorkflowNo/{id}" })
	public GeneralInformation getRecordByWorkflowNo(@PathVariable("id") final String workflowNo) {
		logger.info("inside GeneralInfoController getRecordByWorkflowNo() method");
		return generalInfoRepository.findById(workflowNo)
				.orElseThrow(() -> new ResourceNotFoundException("workflowNo", "id", workflowNo));
	}
}