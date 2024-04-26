package com.abhi.empanelment.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.empanelment.exception.ResourceNotFoundException;
import com.abhi.empanelment.model.BankIFSC;
import com.abhi.empanelment.repository.BankIFSCRepository;



@RestController
@RequestMapping("/api/bankIfsc")
public class BankIFSCController {
	
	private final Logger logger = LoggerFactory.getLogger(BankIFSCController.class);

	@Autowired
	BankIFSCRepository bankIFSCRepository;
	
	@GetMapping("/getAllIfscCodes")
	public List<BankIFSC> getAllRecords() {
		logger.info("inside BankIFSCController getAllRecords() method");
		return bankIFSCRepository.findAll();
	}

	@PostMapping("/saveIfscCode")
	public BankIFSC createRecord(@Valid @RequestBody BankIFSC bankIfsc) {
		logger.info("inside BankIFSCController createRecord() method");
		return bankIFSCRepository.save(bankIfsc);
	}

	@GetMapping("/findByIfscCode/{ifsccode}")
	public BankIFSC getRecordByIfscCode(@PathVariable(value = "ifsccode") String ifscCode) {
		logger.info("inside BankIFSCController getRecordByIfscCode() method");
		return bankIFSCRepository.findByIfscCode(ifscCode);
	}
	
}