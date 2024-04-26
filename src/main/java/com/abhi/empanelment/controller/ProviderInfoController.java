package com.abhi.empanelment.controller;

import com.abhi.empanelment.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import com.abhi.empanelment.model.ProviderInformation;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.abhi.empanelment.repository.ProviderInfoRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({ "/api/providerInfo" })
public class ProviderInfoController {
	private final Logger logger = LoggerFactory.getLogger(ProviderInfoController.class);

	@Autowired
	ProviderInfoRepository providerInfoRepository;

	@GetMapping({ "/getAll" })
	public List<ProviderInformation> getAllRecords() {
		logger.info("inside ProviderInfoController getAllRecords() method");
		return providerInfoRepository.findAll();
	}

	@PostMapping({ "/saveProviderInfo" })
	public ProviderInformation createRecord(@Valid @RequestBody ProviderInformation providerInfo) {
		logger.info("inside ProviderInfoController createRecord() method");
		return providerInfoRepository.save(providerInfo);
	}

	@GetMapping({ "/getByWorkflowNo/{id}" })
	public ProviderInformation getRecordByWorkflowNo(@PathVariable("id") final String workflowNo) {
		logger.info("inside ProviderInfoController getRecordByWorkflowNo() method");
		return providerInfoRepository.findById(workflowNo)
				.orElseThrow(() -> new ResourceNotFoundException("workflowNo", "id", workflowNo));
	}
}