/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.jobs.model;

/**
 *
 * @author boniface
 */
public class JobsBean {
    private Long jobsId;
    private String title;
    private String description;
    private String code;
    private String cadre;
    private String salaryGrade;
    private String jobClassification;

    /**
     * @return the jobsId
     */
    public Long getJobsId() {
        return jobsId;
    }

    /**
     * @param jobsId the jobsId to set
     */
    public void setJobsId(Long jobsId) {
        this.jobsId = jobsId;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return the cadre
     */
    public String getCadre() {
        return cadre;
    }

    /**
     * @param cadre the cadre to set
     */
    public void setCadre(String cadre) {
        this.cadre = cadre;
    }

    /**
     * @return the salaryGrade
     */
    public String getSalaryGrade() {
        return salaryGrade;
    }

    /**
     * @param salaryGrade the salaryGrade to set
     */
    public void setSalaryGrade(String salaryGrade) {
        this.salaryGrade = salaryGrade;
    }

    /**
     * @return the jobClassification
     */
    public String getJobClassification() {
        return jobClassification;
    }

    /**
     * @param jobClassification the jobClassification to set
     */
    public void setJobClassification(String jobClassification) {
        this.jobClassification = jobClassification;
    }


}
