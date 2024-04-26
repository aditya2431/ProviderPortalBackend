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
import com.abhi.empanelment.model.SubSpecialization;
import com.abhi.empanelment.repository.SubSpecializationRepository;



@RestController
@RequestMapping("/api/subSpecialization")
public class SubSpecializationController {
	
	private final Logger logger = LoggerFactory.getLogger(SubSpecializationController.class);

	@Autowired
	SubSpecializationRepository subSpecializationRepository;
	
	@GetMapping("/getAllSubCategories")
	public List<SubSpecialization> getAllSubCategories() {
		logger.info("inside SubSpecializationController getAllSubCategories() method");
		return subSpecializationRepository.findAll();
	}

	@PostMapping("/saveSubCategory")
	public SubSpecialization createRecord(@Valid @RequestBody SubSpecialization subSpecialization) {
		logger.info("inside SubSpecializationController createRecord() method");
		return subSpecializationRepository.save(subSpecialization);
	}

	@GetMapping("/findBySubCategory/{categoryDesc}")
	public SubSpecialization getRecordBySubCategory(@PathVariable(value = "categoryDesc") String categoryName) {
		logger.info("inside SubSpecializationController getRecordBySubCategory() method");
		return subSpecializationRepository.findBySubCategory(categoryName);
	}
}