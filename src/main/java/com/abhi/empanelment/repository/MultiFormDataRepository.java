package com.abhi.empanelment.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.empanelment.model.MultiFormData;

@Repository
public interface MultiFormDataRepository extends JpaRepository<MultiFormData, Long>{

	List<MultiFormData> findByIdAndId(Long workflowNo, Long id);
}
