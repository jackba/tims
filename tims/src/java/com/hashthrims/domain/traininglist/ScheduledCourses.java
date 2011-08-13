/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.traininglist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

/**
 *
 * @author boniface
 */
@Entity
public class ScheduledCourses implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int courseCapacity;
    private int numOfStuds;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRequested;
    private String courseName;
    private String venue;
    private Long venueId;
    private Long courseId;
    private String organisationName;
    private Long courseRequestor;
    private Long organisationId;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "scheduledcourse_id")
    private List<TrainingInstructors> classInstructors = new ArrayList<TrainingInstructors>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ScheduledCourses)) {
            return false;
        }
        ScheduledCourses other = (ScheduledCourses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.traininglist.ScheduledCourses[id=" + id + "]";
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the numOfStuds
     */
    public int getNumOfStuds() {
        return numOfStuds;
    }

    /**
     * @param numOfStuds the numOfStuds to set
     */
    public void setNumOfStuds(int numOfStuds) {
        this.numOfStuds = numOfStuds;
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
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the venue
     */
    public String getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.venue = venue;
    }

    /**
     * @return the venueId
     */
    public Long getVenueId() {
        return venueId;
    }

    /**
     * @param venueId the venueId to set
     */
    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    /**
     * @return the courseId
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the organisationName
     */
    public String getOrganisationName() {
        return organisationName;
    }

    /**
     * @param organisationName the organisationName to set
     */
    public void setOrganisationName(String organisationName) {
        this.organisationName = organisationName;
    }

    /**
     * @return the organisationId
     */
    public Long getOrganisationId() {
        return organisationId;
    }

    /**
     * @param organisationId the organisationId to set
     */
    public void setOrganisationId(Long organisationId) {
        this.organisationId = organisationId;
    }

    /**
     * @return the classInstructors
     */
    public List<TrainingInstructors> getClassInstructors() {
        return classInstructors;
    }

    /**
     * @param classInstructors the classInstructors to set
     */
    public void setClassInstructors(List<TrainingInstructors> classInstructors) {
        this.classInstructors = classInstructors;
    }

    /**
     * @return the courseRequestor
     */
    public Long getCourseRequestor() {
        return courseRequestor;
    }

    /**
     * @param courseRequestor the courseRequestor to set
     */
    public void setCourseRequestor(Long courseRequestor) {
        this.courseRequestor = courseRequestor;
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
     * @return the courseRequestor
     */
}
