package com.abhi.empanelment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.abhi.empanelment.model.SubSpecialization;

public interface SubSpecializationRepository extends JpaRepository<SubSpecialization, Integer>{

	SubSpecialization findBySubCategory(String categoryName);
}
