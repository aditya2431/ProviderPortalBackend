package com.abhi.empanelment.controller;

import com.abhi.empanelment.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import com.abhi.empanelment.model.FinancialInformation;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.abhi.empanelment.repository.FinancialInfoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/financialInfo" })
public class FinancialInfoController {
	private final Logger logger = LoggerFactory.getLogger(FinancialInfoController.class);

	@Autowired
	FinancialInfoRepository financialInfoRepository;

	@GetMapping({ "/getAll" })
	public List<FinancialInformation> getAllRecords() {
		logger.info("inside FinancialInfoController getAllRecords() method");
		return financialInfoRepository.findAll();
	}

	@PostMapping({ "/saveFinancialInfo" })
	public FinancialInformation createRecord(@Valid @RequestBody FinancialInformation infraInfo) {
		logger.info("inside FinancialInfoController createRecord() method");
		return financialInfoRepository.save(infraInfo);
	}

	@GetMapping({ "/getByWorkflowNo/{id}" })
	public FinancialInformation getRecordByWorkflowNo(@PathVariable("id") final String workflowNo) {
		logger.info("inside FinancialInfoController getRecordByWorkflowNo() method");
		return financialInfoRepository.findById(workflowNo)
				.orElseThrow(() -> new ResourceNotFoundException("workflowNo", "id", (Object) workflowNo));
	}
}