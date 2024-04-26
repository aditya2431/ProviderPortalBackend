package com.abhi.empanelment.model;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import org.apache.tomcat.util.json.JSONParser;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.JsonNode;


@Entity
@Table(name = "multiForm_Data")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)


public class MultiFormData {
	 @Id
	    //@GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "workflowNo", nullable = false)
	    private String workflowNo;

	    @Column(name = "ID", nullable = false)
	    private int id;

	    
	    @Column(name = "Request",columnDefinition = "json",nullable = false)
	    private String request;

		public String getWorkflowNo() {
			return workflowNo;
		}

		public void setWorkflowNo(String workflowNo) {
			this.workflowNo = workflowNo;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public String getRequest() {
			return request;
		}

		public void setRequest(String request) {
			this.request = request;
		}
	    
		


}
