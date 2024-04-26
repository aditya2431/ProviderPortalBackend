package com.abhi.empanelment.repository;

import org.springframework.stereotype.Repository;
import com.abhi.empanelment.model.ProviderInformation;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ProviderInfoRepository extends JpaRepository<ProviderInformation, String>
{
    ProviderInformation findByWorkflowNo(final String workflowNo);
}