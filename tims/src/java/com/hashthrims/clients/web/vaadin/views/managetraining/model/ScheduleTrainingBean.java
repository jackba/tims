/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.managetraining.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ScheduleTrainingBean {
    private Long id;

    //Dates
    private Date dateRequested;
    private Date courseStartDate;
    private Date courseEndDate;
    //Strings
    private Long course;
    private Long venue;
    private Long requestor;

    //variables for people selectors;
    private int courseCapacity;
    private List<Long> trainers = new ArrayList<Long>();;
   

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
     * @return the trainers
     */
    public List<Long> getTrainers() {
        return trainers;
    }

    /**
     * @param trainers the trainers to set
     */
    public void setTrainers(List<Long> trainers) {
        this.setTrainers(trainers);
    }

    /**
     * @return the courseCapacity
     */
    public int getCourseCapacity() {
        return courseCapacity;
    }

    /**
     * @param courseCapacity the courseCapacity to set
     */
    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }

    /**
     * @return the venue
     */
    public Long getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(Long venue) {
        this.venue = venue;
    }

    

}
