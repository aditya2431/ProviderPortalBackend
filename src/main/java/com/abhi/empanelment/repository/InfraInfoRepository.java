package com.abhi.empanelment.repository;

import org.springframework.stereotype.Repository;
import com.abhi.empanelment.model.InfraInformation;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface InfraInfoRepository extends JpaRepository<InfraInformation, String>
{
    InfraInformation findByWorkflowNo(final String workflowNo);
}