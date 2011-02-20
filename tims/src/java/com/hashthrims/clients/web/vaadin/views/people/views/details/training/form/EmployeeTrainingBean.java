/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.training.form;

import java.util.Date;

/**
 *
 * @author boniface
 */
public class EmployeeTrainingBean {

    private Long id;
    private String schedule;
    private Date requestedDate;
    private String courseEvaluation;
    private String courseRequestor;
    private String notes;
    private String retraining;
    private String  competency;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the schedule
     */
    public String getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    /**
     * @return the requestedDate
     */
    public Date getRequestedDate() {
        return requestedDate;
    }

    /**
     * @param requestedDate the requestedDate to set
     */
    public void setRequestedDate(Date requestedDate) {
        this.requestedDate = requestedDate;
    }

    /**
     * @return the courseEvaluation
     */
    public String getCourseEvaluation() {
        return courseEvaluation;
    }

    /**
     * @param courseEvaluation the courseEvaluation to set
     */
    public void setCourseEvaluation(String courseEvaluation) {
        this.courseEvaluation = courseEvaluation;
    }

    /**
     * @return the courseRequestor
     */
    public String getCourseRequestor() {
        return courseRequestor;
    }

    /**
     * @param courseRequestor the courseRequestor to set
     */
    public void setCourseRequestor(String courseRequestor) {
        this.courseRequestor = courseRequestor;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the retraining
     */
    public String getRetraining() {
        return retraining;
    }

    /**
     * @param retraining the retraining to set
     */
    public void setRetraining(String retraining) {
        this.retraining = retraining;
    }

    /**
     * @return the competency
     */
    public String getCompetency() {
        return competency;
    }

    /**
     * @param competency the competency to set
     */
    public void setCompetency(String competency) {
        this.competency = competency;
    }

    
}
