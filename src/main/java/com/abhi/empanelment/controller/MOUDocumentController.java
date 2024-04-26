package com.abhi.empanelment.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.empanelment.exception.ResourceNotFoundException;
import com.abhi.empanelment.message.ResponseMessage;
import com.abhi.empanelment.model.MOUDocument;
import com.abhi.empanelment.repository.MOUDocumentRepository;
import com.abhi.empanelment.service.MOUDocumentService;



@RestController
@RequestMapping("/api/mouDocument")
public class MOUDocumentController {
	
	private final Logger logger = LoggerFactory.getLogger(MOUDocumentController.class);

	@Autowired
	MOUDocumentRepository mouDocumentRepository;
	
	@Autowired
	MOUDocumentService mouDocumentService;
	
	@GetMapping("/getAllMOUDocRecords")
	public List<MOUDocument> getAllMOUDocRecords() {
		logger.info("inside MOUDocumentController getAllMOUDocRecords() method");
		return mouDocumentRepository.findAll();
	}

	@PostMapping("/saveMOUDocRecord")
	public MOUDocument createMOURecord(@Valid @RequestBody MOUDocument mouDocument) {
		logger.info("inside MOUDocumentController createMOURecord() method");
		ResponseEntity<ResponseMessage> response = null;
		MOUDocument respObject = mouDocumentRepository.save(mouDocument);
		response = mouDocumentService.callClickPSSMOUAPI(respObject);
		if(response.getStatusCode() == HttpStatus.OK) {
		return respObject;
		}else {
			logger.info("Issue occured while generating MOU document for workflow: "+respObject.getWorkflowNo());
			return respObject;
		}
	}

	@GetMapping("/getMOUByWorkflowNo/{workflowNo}")
	public MOUDocument getMOUByWorkflowNo(@PathVariable(value = "workflowNo") String workflowNumber) {
		logger.info("inside MOUDocumentController getMOUByWorkflowNo() method");
		return mouDocumentRepository.findById(workflowNumber)
				.orElseThrow(() -> new ResourceNotFoundException("workflowNo", "id", workflowNumber));
	}
	
}