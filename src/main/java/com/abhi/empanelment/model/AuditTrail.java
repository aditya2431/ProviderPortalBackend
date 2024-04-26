package com.abhi.empanelment.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.persistence.Id;

import com.abhi.empanelment.generator.StringPrefixedSequenceIdGenerator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
@Table(name = "audittrail_details")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class AuditTrail
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	
    @NotBlank
    @Column(name = "workflow_no", updatable = false, nullable = false)
    private String workflowNo;
    @NotBlank
    private String taskName;
    @NotBlank
    private String queueName;
    @NotBlank
    private String action;
    @NotBlank
    private String actionedBy;
    
    private Date actionedDate;
    private int sla;
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getWorkflowNo() {
		return workflowNo;
	}
	public void setWorkflowNo(String workflowNo) {
		this.workflowNo = workflowNo;
	}
	public String getTaskName() {
		return taskName;
	}
	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}
	public String getQueueName() {
		return queueName;
	}
	public void setQueueName(String queueName) {
		this.queueName = queueName;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getActionedBy() {
		return actionedBy;
	}
	public void setActionedBy(String actionedBy) {
		this.actionedBy = actionedBy;
	}
	public Date getActionedDate() {
		return actionedDate;
	}
	public void setActionedDate(Date actionedDate) {
		this.actionedDate = actionedDate;
	}
	public int getSla() {
		return sla;
	}
	public void setSla(int sla) {
		this.sla = sla;
	}
    
    
}