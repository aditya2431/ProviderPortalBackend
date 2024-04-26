package com.abhi.empanelment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abhi.empanelment.model.Discount;

public interface DiscountRepository extends JpaRepository<Discount, Integer>{
	List<Discount> findByWorkflowNo(String workflowNo);
}