package com.abhi.empanelment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abhi.empanelment.model.Specialization;

public interface SpecializationRepository extends JpaRepository<Specialization, Integer>{

	Specialization findByCategory(String categoryName);
}
