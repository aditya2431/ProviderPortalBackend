package com.abhi.empanelment.repository;

import org.springframework.stereotype.Repository;
import com.abhi.empanelment.model.GeneralInformation;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GeneralInfoRepository extends JpaRepository<GeneralInformation, String>
{
    GeneralInformation findByWorkflowNo(final String workflowNo);
}