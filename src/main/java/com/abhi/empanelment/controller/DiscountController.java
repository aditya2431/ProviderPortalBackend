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
import com.abhi.empanelment.model.Discount;
import com.abhi.empanelment.repository.DiscountRepository;



@RestController
@RequestMapping("/api/discount")
public class DiscountController {
	
	private final Logger logger = LoggerFactory.getLogger(DiscountController.class);

	@Autowired
	DiscountRepository discountRepository;
	
	@GetMapping("/getAll")
	public List<Discount> getAll() {
		logger.info("inside DiscountController getAll() method");
		return discountRepository.findAll();
	}

	@PostMapping("/saveAll")
	public List<Discount> saveAll(@Valid @RequestBody List<Discount> subSpecialization) {
		logger.info("inside DiscountController saveAll() method");
		return discountRepository.saveAll(subSpecialization);
	}
}