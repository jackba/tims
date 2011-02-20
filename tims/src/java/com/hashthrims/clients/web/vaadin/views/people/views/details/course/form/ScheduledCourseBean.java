/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.course.form;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author boniface
 */
public class ScheduledCourseBean implements Serializable {

    private Long id;
    //Dates
    private Date dateRequested;
    private Date courseStartDate;
    private Date courseEndDate;
    //Strings
    private Long course;
    private String retraining;
    private Long requestor;
  

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

}
