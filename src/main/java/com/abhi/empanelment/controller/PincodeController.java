package com.abhi.empanelment.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

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

import com.abhi.empanelment.exception.ResourceNotFoundException;
import com.abhi.empanelment.model.Pincode;
import com.abhi.empanelment.repository.PincodeRepository;



@RestController
@RequestMapping("/api/pincode")
public class PincodeController {
	
	private final Logger logger = LoggerFactory.getLogger(PincodeController.class);

	@Autowired
	PincodeRepository pincodeRepository;
	
	@GetMapping("/getAllPincodes")
	public List<Pincode> getAllRecords() {
		logger.info("inside PincodeController getAllRecords() method");
		return pincodeRepository.findAll();
	}

	@PostMapping("/savePincode")
	public Pincode createRecord(@Valid @RequestBody Pincode pincode) {
		logger.info("inside PincodeController createRecord() method");
		return pincodeRepository.save(pincode);
	}

	@GetMapping("/findByPincode/{pincode}")
	public Pincode getRecordByPincode(@PathVariable(value = "pincode") String pinCode) {
		logger.info("inside PincodeController getRecordByPincode() method");
		return pincodeRepository.findByPinCode(pinCode);
	}
	@GetMapping("/suggestions")
    public ResponseEntity<List<String>> getSuggestions(@RequestParam String partialPincode) {
        List<String> suggestions = pincodeRepository.findSuggestionsByPartialPincode(partialPincode);
        return ResponseEntity.ok(suggestions);
    }
	
}
