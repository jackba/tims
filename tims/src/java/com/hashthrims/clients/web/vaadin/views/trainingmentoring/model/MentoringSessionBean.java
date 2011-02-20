/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.model;

import java.util.List;

/**
 *
 * @author boniface
 */
public class MentoringSessionBean {

    private Long id;
    private String sessionName;
    private String mentoringTheme;
    private String institutionName;
    private String sessionStatus;
    private String mentoringNotes;
    private String sessionType;
    private String competencyType;
    private List<String> mentoringFunders;
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
     * @return the mentoringTheme
     */
    public String getMentoringTheme() {
        return mentoringTheme;
    }

    /**
     * @param mentoringTheme the mentoringTheme to set
     */
    public void setMentoringTheme(String mentoringTheme) {
        this.mentoringTheme = mentoringTheme;
    }

    /**
     * @return the institutionName
     */
    public String getInstitutionName() {
        return institutionName;
    }

    /**
     * @param institutionName the institutionName to set
     */
    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    /**
     * @return the sessionStatus
     */
    public String getSessionStatus() {
        return sessionStatus;
    }

    /**
     * @param sessionStatus the sessionStatus to set
     */
    public void setSessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    /**
     * @return the mentoringNotes
     */
    public String getMentoringNotes() {
        return mentoringNotes;
    }

    /**
     * @param mentoringNotes the mentoringNotes to set
     */
    public void setMentoringNotes(String mentoringNotes) {
        this.mentoringNotes = mentoringNotes;
    }

    /**
     * @return the mentoringFunders
     */
    public List<String> getMentoringFunders() {
        return mentoringFunders;
    }

    /**
     * @param mentoringFunders the mentoringFunders to set
     */
    public void setMentoringFunders(List<String> mentoringFunders) {
        this.mentoringFunders = mentoringFunders;
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
        this.mentoringCompetencies = mentoringCompetencies;
    }


    /**
     * @return the sessionType
     */
    public String getSessionType() {
        return sessionType;
    }

    /**
     * @param sessionType the sessionType to set
     */
    public void setSessionType(String sessionType) {
        this.sessionType = sessionType;
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
        this.sessionMentors = sessionMentors;
    }

    /**
     * @return the competencyType
     */
    public String getCompetencyType() {
        return competencyType;
    }

    /**
     * @param competencyType the competencyType to set
     */
    public void setCompetencyType(String competencyType) {
        this.competencyType = competencyType;
    }
}
