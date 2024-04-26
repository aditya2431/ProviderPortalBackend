package com.abhi.empanelment.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotBlank;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.EntityListeners;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "infrastructure_information")
@EntityListeners({ AuditingEntityListener.class })
@JsonIgnoreProperties(allowGetters = true)
public class InfraInformation
{
    @Id
    @NotBlank
    private String workflowNo;
    @NotNull
    private int noOfBeds;
    @NotNull
    private int noOfDoctors;
    @NotNull
    private int noOfFulltimeDoctors;
    @NotNull
    private int noOfConsultants;
    @NotNull
    private int noOfSurgeons;
    @NotNull
    private int noOfNurses;
    @NotNull
    private int noOfICUBeds;
    @NotNull
    private int noOfICUDoctors;
    @NotNull
    private int noOfICUNurses;
    @NotBlank
    private String accreditationReceived;
    @NotBlank
    private String doctorToBedRatio;
    @NotBlank
    private String nurseToBedRatio;
    @NotBlank
    private String doctorToBedICURatio;
    @NotBlank
    private String nurseToBedICURatio;
    @NotBlank
    private String averageAdmissionTime;
    @NotBlank
    private String averageDischargeTime;
    @NotBlank
    private String averageStayForMedicalCases;
    @NotBlank
    private String averageStayForSurgicalCases;
    @NotBlank
    private String cSectionRate;
  
    private String websiteLink;
    
    public String getWorkflowNo() {
        return this.workflowNo;
    }
    
    public void setWorkflowNo(final String workflowNo) {
        this.workflowNo = workflowNo;
    }
    
    public int getNoOfBeds() {
        return this.noOfBeds;
    }
    
    public void setNoOfBeds(final int noOfBeds) {
        this.noOfBeds = noOfBeds;
    }
    
    public int getNoOfDoctors() {
        return this.noOfDoctors;
    }
    
    public void setNoOfDoctors(final int noOfDoctors) {
        this.noOfDoctors = noOfDoctors;
    }
    
    public int getNoOfFulltimeDoctors() {
        return this.noOfFulltimeDoctors;
    }
    
    public void setNoOfFulltimeDoctors(final int noOfFulltimeDoctors) {
        this.noOfFulltimeDoctors = noOfFulltimeDoctors;
    }
    
    public int getNoOfConsultants() {
        return this.noOfConsultants;
    }
    
    public void setNoOfConsultants(final int noOfConsultants) {
        this.noOfConsultants = noOfConsultants;
    }
    
    public int getNoOfSurgeons() {
        return this.noOfSurgeons;
    }
    
    public void setNoOfSurgeons(final int noOfSurgeons) {
        this.noOfSurgeons = noOfSurgeons;
    }
    
    public int getNoOfNurses() {
        return this.noOfNurses;
    }
    
    public void setNoOfNurses(final int noOfNurses) {
        this.noOfNurses = noOfNurses;
    }
    
    public int getNoOfICUBeds() {
        return this.noOfICUBeds;
    }
    
    public void setNoOfICUBeds(final int noOfICUBeds) {
        this.noOfICUBeds = noOfICUBeds;
    }
    
    public int getNoOfICUDoctors() {
        return this.noOfICUDoctors;
    }
    
    public void setNoOfICUDoctors(final int noOfICUDoctors) {
        this.noOfICUDoctors = noOfICUDoctors;
    }
    
    public int getNoOfICUNurses() {
        return this.noOfICUNurses;
    }
    
    public void setNoOfICUNurses(final int noOfICUNurses) {
        this.noOfICUNurses = noOfICUNurses;
    }
    
    public String getAccreditationReceived() {
        return this.accreditationReceived;
    }
    
    public void setAccreditationReceived(final String accreditationReceived) {
        this.accreditationReceived = accreditationReceived;
    }
    
    public String getDoctorToBedRatio() {
        return this.doctorToBedRatio;
    }
    
    public void setDoctorToBedRatio(final String doctorToBedRatio) {
        this.doctorToBedRatio = doctorToBedRatio;
    }
    
    public String getNurseToBedRatio() {
        return this.nurseToBedRatio;
    }
    
    public void setNurseToBedRatio(final String nurseToBedRatio) {
        this.nurseToBedRatio = nurseToBedRatio;
    }
    
    public String getDoctorToBedICURatio() {
        return this.doctorToBedICURatio;
    }
    
    public void setDoctorToBedICURatio(final String doctorToBedICURatio) {
        this.doctorToBedICURatio = doctorToBedICURatio;
    }
    
    public String getNurseToBedICURatio() {
        return this.nurseToBedICURatio;
    }
    
    public void setNurseToBedICURatio(final String nurseToBedICURatio) {
        this.nurseToBedICURatio = nurseToBedICURatio;
    }
    
    public String getAverageAdmissionTime() {
        return this.averageAdmissionTime;
    }
    
    public void setAverageAdmissionTime(final String averageAdmissionTime) {
        this.averageAdmissionTime = averageAdmissionTime;
    }
    
    public String getAverageDischargeTime() {
        return this.averageDischargeTime;
    }
    
    public void setAverageDischargeTime(final String averageDischargeTime) {
        this.averageDischargeTime = averageDischargeTime;
    }
    
    public String getAverageStayForMedicalCases() {
        return this.averageStayForMedicalCases;
    }
    
    public void setAverageStayForMedicalCases(final String averageStayForMedicalCases) {
        this.averageStayForMedicalCases = averageStayForMedicalCases;
    }
    
    public String getAverageStayForSurgicalCases() {
        return this.averageStayForSurgicalCases;
    }
    
    public void setAverageStayForSurgicalCases(final String averageStayForSurgicalCases) {
        this.averageStayForSurgicalCases = averageStayForSurgicalCases;
    }
    
    public String getcSectionRate() {
        return this.cSectionRate;
    }
    
    public void setcSectionRate(final String cSectionRate) {
        this.cSectionRate = cSectionRate;
    }
    
    public String getWebsiteLink() {
        return this.websiteLink;
    }
    
    public void setWebsiteLink(final String websiteLink) {
        this.websiteLink = websiteLink;
    }
}