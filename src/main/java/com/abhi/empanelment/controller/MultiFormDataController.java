package com.abhi.empanelment.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.empanelment.model.MultiFormData;
import com.abhi.empanelment.service.MultiFormDataService;

@RestController
@RequestMapping({ "/api/multiFormData" })
public class MultiFormDataController {
	private final MultiFormDataService service;

	private final Logger logger = LoggerFactory.getLogger(MultiFormDataController.class);

	@Autowired
	public MultiFormDataController(MultiFormDataService service) {
		this.service = service;
	}

	@PostMapping("/save")
	public ResponseEntity<MultiFormData> saveData(@RequestBody MultiFormData requestBody) {
		logger.info("inside MultiFormDataController saveData() method");
		System.out.println("json request is" + requestBody.getRequest());
		MultiFormData entity = service.saveData(requestBody.getWorkflowNo(), requestBody.getId(),
				requestBody.getRequest());
		return ResponseEntity.ok(entity);
	}

	@GetMapping("/retrieve/{workflowNo}")
	public ResponseEntity<MultiFormData> retrieveData(@PathVariable Long workflowNo) {
		logger.info("inside MultiFormDataController retrieveData() method");
		MultiFormData entity = service.getDataByWorkflowNo(workflowNo);
		return ResponseEntity.ok(entity);
	}

	@GetMapping("/retrieve/{workflowNo}/{id}")
	public ResponseEntity<List<MultiFormData>> retrieveDataById(@PathVariable Long workflowNo, @PathVariable Long id) {
		logger.info("inside MultiFormDataController retrieveDataById() method");
		List<MultiFormData> entities = service.getDataByWorkflowNoAndId(workflowNo, id);
		return ResponseEntity.ok(entities);
	}
}
