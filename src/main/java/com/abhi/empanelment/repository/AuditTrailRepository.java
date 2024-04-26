package com.abhi.empanelment.repository;

import org.springframework.stereotype.Repository;
import com.abhi.empanelment.model.AuditTrail;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

@Repository
public interface AuditTrailRepository extends JpaRepository<AuditTrail, Integer>
{
	@Query("SELECT id, action, actionedBy, actionedDate, queueName, taskName, workflowNo, "
			+ "datediff(sysdate(), actioned_date)  as sla  FROM AuditTrail "
			+ "where workflow_no = :workflowNo ")
    List<Object[]> findByWorkflowNo(String workflowNo);
}