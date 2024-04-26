package com.abhi.empanelment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abhi.empanelment.model.EPDDiscount;

public interface EPDDiscountRepository extends JpaRepository<EPDDiscount, Integer>{
	List<EPDDiscount> findByWorkflowNo(String workflowNo);
}