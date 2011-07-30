/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.model;

import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class MentoringSessionBean {

    private Long id;
    private String sessionName;
    private Long mentoringSubjectArea;
    
   
    private Long institutionName;
    private Date startDate;
    private Date endDate;
    
    private Long sessionStatus;
    private Long mentoringVenue;
    
    private List<Long> mentoringFunders;
    private List<Long> sessionMentors;
    
    private List<Long> mentoringObjectives;
    private List<Long> mentoringThemes;
    private List<Long> mentoringSessionType;
    private List<Long> areasOfStrenthening;

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
     * @return the sessionName
     */
    public String getSessionName() {
        return sessionName;
    }

    /**
     * @param sessionName the sessionName to set
     */
    public void setSessionName(String sessionName) {
        this.sessionName = sessionName;
    }

    /**
     * @return the institutionName
     */
    public Long getInstitutionName() {
        return institutionName;
    }

    /**
     * @param institutionName the institutionName to set
     */
    public void setInstitutionName(Long institutionName) {
        this.institutionName = institutionName;
    }

    /**
     * @return the sessionStatus
     */
    public Long getSessionStatus() {
        return sessionStatus;
    }

    /**
     * @param sessionStatus the sessionStatus to set
     */
    public void setSessionStatus(Long sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    /**
     * @return the mentoringFunders
     */
    public List<Long> getMentoringFunders() {
        return mentoringFunders;
    }

    /**
     * @param mentoringFunders the mentoringFunders to set
     */
    public void setMentoringFunders(List<Long> mentoringFunders) {
        this.mentoringFunders = mentoringFunders;
    }

    /**
     * @return the sessionMentors
     */
    public List<Long> getSessionMentors() {
        return sessionMentors;
    }

    /**
     * @param sessionMentors the sessionMentors to set
     */
    public void setSessionMentors(List<Long> sessionMentors) {
        this.sessionMentors = sessionMentors;
    }

    /**
     * @return the mentoringObjectives
     */
    public List<Long> getMentoringObjectives() {
        return mentoringObjectives;
    }

    /**
     * @param mentoringObjectives the mentoringObjectives to set
     */
    public void setMentoringObjectives(List<Long> mentoringObjectives) {
        this.mentoringObjectives = mentoringObjectives;
    }

    /**
     * @return the mentoringThemes
     */
    public List<Long> getMentoringThemes() {
        return mentoringThemes;
    }

    /**
     * @param mentoringThemes the mentoringThemes to set
     */
    public void setMentoringThemes(List<Long> mentoringThemes) {
        this.mentoringThemes = mentoringThemes;
    }

    /**
     * @return the mentoringSessionType
     */
    public List<Long> getMentoringSessionType() {
        return mentoringSessionType;
    }

    /**
     * @param mentoringSessionType the mentoringSessionType to set
     */
    public void setMentoringSessionType(List<Long> mentoringSessionType) {
        this.mentoringSessionType = mentoringSessionType;
    }

    /**
     * @return the mentoringSubjectArea
     */
    public Long getMentoringSubjectArea() {
        return mentoringSubjectArea;
    }

    /**
     * @param mentoringSubjectArea the mentoringSubjectArea to set
     */
    public void setMentoringSubjectArea(Long mentoringSubjectArea) {
        this.mentoringSubjectArea = mentoringSubjectArea;
    }

    /**
     * @return the mentoringVenue
     */
    public Long getMentoringVenue() {
        return mentoringVenue;
    }

    /**
     * @param mentoringVenue the mentoringVenue to set
     */
    public void setMentoringVenue(Long mentoringVenue) {
        this.mentoringVenue = mentoringVenue;
    }

    /**
     * @return the areasOfStrenthening
     */
    public List<Long> getAreasOfStrenthening() {
        return areasOfStrenthening;
    }

    /**
     * @param areasOfStrenthening the areasOfStrenthening to set
     */
    public void setAreasOfStrenthening(List<Long> areasOfStrenthening) {
        this.areasOfStrenthening = areasOfStrenthening;
    }

    /**
     * @return the StartDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param StartDate the StartDate to set
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
}
