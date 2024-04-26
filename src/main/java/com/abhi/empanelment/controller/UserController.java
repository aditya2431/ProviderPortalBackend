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
import com.abhi.empanelment.model.User;
import com.abhi.empanelment.repository.UserRepository;



@RestController
@RequestMapping("/api/user")
public class UserController {
	
	private final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/getAllUsers")
	public List<User> getAllRecords() {
		logger.info("inside UserController getAllRecords() method");
		return userRepository.findAll();
	}

	@PostMapping("/saveUser")
	public User createRecord(@Valid @RequestBody User user) {
		logger.info("inside UserController createRecord() method");
		return userRepository.save(user);
	}

	@GetMapping("/findByUserId/{adId}")
	public User getRecordById(@PathVariable(value = "adId") String name) {
		logger.info("inside UserController getRecordById() method");
		return userRepository.findById(name)
				.orElseThrow(() -> new ResourceNotFoundException("User", "adId", name));
	}
	
	@GetMapping("/findByZM/{ZM}")
	public List<User> getRecordByAbhiManager(@PathVariable String ZM) {
		logger.info("inside UserController getRecordByAbhiManager() method");
		return userRepository.findByZmAdId(ZM);
	}
	
	@GetMapping("/findByRM/{adId}")
	public List<User> getByAdId(@PathVariable String adId) {
		logger.info("inside UserController getByAdId() method");
		return userRepository.findByAdId(adId);
	}
	
}
