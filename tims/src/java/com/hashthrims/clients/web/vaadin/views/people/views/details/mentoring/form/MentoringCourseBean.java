/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring.form;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author boniface
 */
public class MentoringCourseBean implements Serializable {

    private Long id;
    private Long patientsInitiated;
    private Long hoursSpent;
    private Date mentoringDate;
    private Long mentoringSession;
    private Long faciltyType;
    private Long venue;

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
     * @return the patientsInitiated
     */
    public Long getPatientsInitiated() {
        return patientsInitiated;
    }

    /**
     * @param patientsInitiated the patientsInitiated to set
     */
    public void setPatientsInitiated(Long patientsInitiated) {
        this.patientsInitiated = patientsInitiated;
    }

    /**
     * @return the hoursSpent
     */
    public Long getHoursSpent() {
        return hoursSpent;
    }

    /**
     * @param hoursSpent the hoursSpent to set
     */
    public void setHoursSpent(Long hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    /**
     * @return the mentoringDate
     */
    public Date getMentoringDate() {
        return mentoringDate;
    }

    /**
     * @param mentoringDate the mentoringDate to set
     */
    public void setMentoringDate(Date mentoringDate) {
        this.mentoringDate = mentoringDate;
    }

    

    /**
     * @param venue the venue to set
     */
    public void setVenue(String venue) {
        this.setVenue(venue);
    }

    /**
     * @return the mentoringSession
     */
    public Long getMentoringSession() {
        return mentoringSession;
    }

    /**
     * @param mentoringSession the mentoringSession to set
     */
    public void setMentoringSession(Long mentoringSession) {
        this.mentoringSession = mentoringSession;
    }

    /**
     * @return the faciltyType
     */
    public Long getFaciltyType() {
        return faciltyType;
    }

    /**
     * @param faciltyType the faciltyType to set
     */
    public void setFaciltyType(Long faciltyType) {
        this.faciltyType = faciltyType;
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
