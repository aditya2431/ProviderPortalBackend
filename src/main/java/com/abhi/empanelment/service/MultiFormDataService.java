package com.abhi.empanelment.service;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abhi.empanelment.model.MultiFormData;
import com.abhi.empanelment.repository.MultiFormDataRepository;
import com.fasterxml.jackson.databind.JsonNode;
@Service
public class MultiFormDataService {
	
	private final Logger logger = LoggerFactory.getLogger(MultiFormDataService.class);
	private final MultiFormDataRepository repository;

    @Autowired
    public MultiFormDataService(MultiFormDataRepository repository) {
        this.repository = repository;
    }

    public MultiFormData saveData(String workflowNo, int id, String request) {
    	logger.info("inside MultiFormDataService saveData() method");
    	MultiFormData entity = new MultiFormData();
    	entity.setWorkflowNo(workflowNo);
        entity.setId(id);
        entity.setRequest(request);
        return repository.save(entity);
    }

    public MultiFormData getDataByWorkflowNo(Long workflowNo) {
    	logger.info("inside MultiFormDataService getDataByWorkflowNo() method");
        return repository.findById(workflowNo).orElse(null);
    }
    
    public List<MultiFormData> getDataByWorkflowNoAndId(Long workflowNo, Long id) {
    	logger.info("inside MultiFormDataService getDataByWorkflowNoAndId() method");
        return repository.findByIdAndId(workflowNo, id);
    }

}
