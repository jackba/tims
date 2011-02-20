/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.positions.model.dtos;

import java.util.List;

/**
 *
 * @author boniface
 */
public class DependantFields {
        private String salary ;
        private List<String> salarySources ;
        private String facililty ;
        private String supervisor ;
        private String department ;
        private String job;
        private String positionType ;
        private String positionStatus;

    /**
     * @return the salary
     */
    public String getSalary() {
        return salary;
    }

    /**
     * @param salary the salary to set
     */
    public void setSalary(String salary) {
        this.salary = salary;
    }

    /**
     * @return the salarySources
     */
    public List<String> getSalarySources() {
        return salarySources;
    }

    /**
     * @param salarySources the salarySources to set
     */
    public void setSalarySources(List<String> salarySources) {
        this.salarySources = salarySources;
    }

    /**
     * @return the facililty
     */
    public String getFacililty() {
        return facililty;
    }

    /**
     * @param facililty the facililty to set
     */
    public void setFacililty(String facililty) {
        this.facililty = facililty;
    }

    /**
     * @return the supervisor
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }

    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /**
     * @return the job
     */
    public String getJob() {
        return job;
    }

    /**
     * @param job the job to set
     */
    public void setJob(String job) {
        this.job = job;
    }

    /**
     * @return the positionType
     */
    public String getPositionType() {
        return positionType;
    }

    /**
     * @param positionType the positionType to set
     */
    public void setPositionType(String positionType) {
        this.positionType = positionType;
    }

    /**
     * @return the positionStatus
     */
    public String getPositionStatus() {
        return positionStatus;
    }

    /**
     * @param positionStatus the positionStatus to set
     */
    public void setPositionStatus(String positionStatus) {
        this.positionStatus = positionStatus;
    }

}
