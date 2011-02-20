/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.model;

/**
 *
 * @author stud
 */
public class PositionBean {

    private Long positionId;

    private String positionCode;
    private Long positionType;
    private Long positionStatus;
    private String positionComments;

    private Long facililty;
    private Long supervisor;
    private Long department;
    private Long job;

    /**
     * @return the positionId
     */
    public Long getPositionId() {
        return positionId;
    }

    /**
     * @param positionId the positionId to set
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

  
    /**
     * @return the positionCode
     */
    public String getPositionCode() {
        return positionCode;
    }

    /**
     * @param positionCode the positionCode to set
     */
    public void setPositionCode(String positionCode) {
        this.positionCode = positionCode;
    }
    /**
     * @return the positionComments
     */
    public String getPositionComments() {
        return positionComments;
    }

    /**
     * @param positionComments the positionComments to set
     */
    public void setPositionComments(String positionComments) {
        this.positionComments = positionComments;
    }

    /**
     * @return the positionType
     */
    public Long getPositionType() {
        return positionType;
    }

    /**
     * @param positionType the positionType to set
     */
    public void setPositionType(Long positionType) {
        this.positionType = positionType;
    }

    /**
     * @return the positionStatus
     */
    public Long getPositionStatus() {
        return positionStatus;
    }

    /**
     * @param positionStatus the positionStatus to set
     */
    public void setPositionStatus(Long positionStatus) {
        this.positionStatus = positionStatus;
    }

    /**
     * @return the facililty
     */
    public Long getFacililty() {
        return facililty;
    }

    /**
     * @param facililty the facililty to set
     */
    public void setFacililty(Long facililty) {
        this.facililty = facililty;
    }

    /**
     * @return the supervisor
     */
    public Long getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(Long supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * @return the department
     */
    public Long getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(Long department) {
        this.department = department;
    }

    /**
     * @return the job
     */
    public Long getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(Long job) {
        this.job = job;
    }

   

}
