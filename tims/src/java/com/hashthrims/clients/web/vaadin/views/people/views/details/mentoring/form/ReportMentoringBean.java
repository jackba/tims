/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.mentoring.form;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class ReportMentoringBean implements Serializable {

    private Long id;
    private Long patientsInitiated;
    private Long hoursSpent;
    private Date mentoringDate;
    private Long typeOfSession;
    private Long faciltyType;
    private Long competencyType;
    private String venue;
    private List<String> mentoringCompetencies;
    private List<String> sessionMentors;

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
     * @return the mentoringCompetencies
     */
    public List<String> getMentoringCompetencies() {
        return mentoringCompetencies;
    }

    /**
     * @param mentoringCompetencies the mentoringCompetencies to set
     */
    public void setMentoringCompetencies(List<String> mentoringCompetencies) {
        this.setMentoringCompetencies(mentoringCompetencies);
    }

    /**
     * @return the sessionMentors
     */
    public List<String> getSessionMentors() {
        return sessionMentors;
    }

    /**
     * @param sessionMentors the sessionMentors to set
     */
    public void setSessionMentors(List<String> sessionMentors) {
        this.setSessionMentors(sessionMentors);
    }

    /**
     * @return the typeOfSession
     */
    public Long getTypeOfSession() {
        return typeOfSession;
    }

    /**
     * @param typeOfSession the typeOfSession to set
     */
    public void setTypeOfSession(Long typeOfSession) {
        this.typeOfSession = typeOfSession;
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
     * @return the competencyType
     */
    public Long getCompetencyType() {
        return competencyType;
    }

    /**
     * @param competencyType the competencyType to set
     */
    public void setCompetencyType(Long competencyType) {
        this.competencyType = competencyType;
    }

   
}
