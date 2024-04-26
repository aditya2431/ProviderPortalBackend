package com.abhi.empanelment.model;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "user")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@NotBlank
	private String adId;
	@NotBlank
	private String rmName;
	@NotBlank
	private String rmEmailId;
	@NotBlank
	private String rmMobileNumber;
	@NotBlank
	private String role;
	@NotBlank
	private String zmAdId;
	@NotBlank
	private String zmName;
	@NotBlank
	private String zmEmailId;
	@NotBlank
	private String zmMobileNumber;
	@NotBlank
	private String location;
	@NotBlank
	private String zone;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAdId() {
		return adId;
	}
	public void setAdId(String adId) {
		this.adId = adId;
	}
	public String getRmName() {
		return rmName;
	}
	public void setRmName(String rmName) {
		this.rmName = rmName;
	}
	public String getRmEmailId() {
		return rmEmailId;
	}
	public void setRmEmailId(String rmEmailId) {
		this.rmEmailId = rmEmailId;
	}
	public String getRmMobileNumber() {
		return rmMobileNumber;
	}
	public void setRmMobileNumber(String rmMobileNumber) {
		this.rmMobileNumber = rmMobileNumber;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public String getZmAdId() {
		return zmAdId;
	}
	public void setZmAdId(String zmAdId) {
		this.zmAdId = zmAdId;
	}
	public String getZmName() {
		return zmName;
	}
	public void setZmName(String zmName) {
		this.zmName = zmName;
	}
	public String getZmEmailId() {
		return zmEmailId;
	}
	public void setZmEmailId(String zmEmailId) {
		this.zmEmailId = zmEmailId;
	}
	public String getZmMobileNumber() {
		return zmMobileNumber;
	}
	public void setZmMobileNumber(String zmMobileNumber) {
		this.zmMobileNumber = zmMobileNumber;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}

}
