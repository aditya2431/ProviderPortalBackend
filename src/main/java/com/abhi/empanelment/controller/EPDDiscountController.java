package com.abhi.empanelment.controller;

import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.abhi.empanelment.model.EPDDiscount;
import com.abhi.empanelment.repository.EPDDiscountRepository;



@RestController
@RequestMapping("/api/epdDiscount")
public class EPDDiscountController {
	
	private final Logger logger = LoggerFactory.getLogger(EPDDiscountController.class);

	@Autowired
	EPDDiscountRepository ePDDiscountRepository;
	
	@GetMapping("/getAll")
	public List<EPDDiscount> getAll() {
		logger.info("inside EPDDiscountController getAll() method");
		return ePDDiscountRepository.findAll();
	}

	@PostMapping("/saveAll")
	public List<EPDDiscount> saveAll(@Valid @RequestBody List<EPDDiscount> subSpecialization) {
		logger.info("inside EPDDiscountController saveAll() method");
		return ePDDiscountRepository.saveAll(subSpecialization);
	}
}