/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.managetraining.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ManageTrainingBean {
    private Long id;

    //Dates
    private Date dateRequested;
    private Date courseStartDate;
    private Date courseEndDate;
    //Strings
    private Long course;
    private String retraining;
    private Long requestor;

    //variables for people selectors;
    private Long facultyId;
    private List<Long> trainees;

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
     * @return the dateRequested
     */
    public Date getDateRequested() {
        return dateRequested;
    }

    /**
     * @param dateRequested the dateRequested to set
     */
    public void setDateRequested(Date dateRequested) {
        this.dateRequested = dateRequested;
    }

    /**
     * @return the courseStartDate
     */
    public Date getCourseStartDate() {
        return courseStartDate;
    }

    /**
     * @param courseStartDate the courseStartDate to set
     */
    public void setCourseStartDate(Date courseStartDate) {
        this.courseStartDate = courseStartDate;
    }

    /**
     * @return the courseEndDate
     */
    public Date getCourseEndDate() {
        return courseEndDate;
    }

    /**
     * @param courseEndDate the courseEndDate to set
     */
    public void setCourseEndDate(Date courseEndDate) {
        this.courseEndDate = courseEndDate;
    }

    /**
     * @return the course
     */
    public Long getCourse() {
        return course;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(Long course) {
        this.course = course;
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
     * @return the requestor
     */
    public Long getRequestor() {
        return requestor;
    }

    /**
     * @param requestor the requestor to set
     */
    public void setRequestor(Long requestor) {
        this.requestor = requestor;
    }

   
    /**
     * @return the facultyId
     */
    public Long getFacultyId() {
        return facultyId;
    }

    /**
     * @param facultyId the facultyId to set
     */
    public void setFacultyId(Long facultyId) {
        this.facultyId = facultyId;
    }

    /**
     * @return the trainees
     */
    public List<Long> getTrainees() {
        return trainees;
    }

    /**
     * @param trainees the trainees to set
     */
    public void setTrainees(List<Long> trainees) {
        this.trainees = trainees;
    }


}
