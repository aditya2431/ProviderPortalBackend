package com.abhi.empanelment.repository;

import org.springframework.stereotype.Repository;
import com.abhi.empanelment.model.FinancialInformation;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface FinancialInfoRepository extends JpaRepository<FinancialInformation, String>
{
    FinancialInformation findByWorkflowNo(final String workflowNo);
}