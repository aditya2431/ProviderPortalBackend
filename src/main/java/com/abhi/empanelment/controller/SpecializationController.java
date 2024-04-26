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
import com.abhi.empanelment.model.Specialization;
import com.abhi.empanelment.repository.SpecializationRepository;



@RestController
@RequestMapping("/api/specialization")
public class SpecializationController {
	
	private final Logger logger = LoggerFactory.getLogger(SpecializationController.class);

	@Autowired
	SpecializationRepository specializationRepository;
	
	@GetMapping("/getAllCategories")
	public List<Specialization> getAllRecords() {
		logger.info("inside SpecializationController getAllRecords() method");
		return specializationRepository.findAll();
	}

	@PostMapping("/saveCategory")
	public Specialization createRecord(@Valid @RequestBody Specialization specialization) {
		logger.info("inside SpecializationController createRecord() method");
		return specializationRepository.save(specialization);
	}

	@GetMapping("/findByCategory/{categoryDesc}")
	public Specialization getRecordByCategory(@PathVariable(value = "categoryDesc") String categoryName) {
		logger.info("inside SpecializationController getRecordByCategory() method");
		return specializationRepository.findByCategory(categoryName);
	}
}