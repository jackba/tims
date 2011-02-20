/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.employeelists.competency.model;

/**
 *
 * @author boniface
 */
public class CompetencyListBean {

    private Long competencyId;
    private String competencyName;
    private String competencyNotes;
    private String competencyType;
  



    /**
     * @return the competencyName
     */
    public String getCompetencyName() {
        return competencyName;
    }

    /**
     * @param competencyName the competencyName to set
     */
    public void setCompetencyName(String competencyName) {
        this.competencyName = competencyName;
    }

    /**
     * @return the competencyNotes
     */
    public String getCompetencyNotes() {
        return competencyNotes;
    }

    /**
     * @param competencyNotes the competencyNotes to set
     */
    public void setCompetencyNotes(String competencyNotes) {
        this.competencyNotes = competencyNotes;
    }

    

    /**
     * @return the comEvaluationId
     */
    public Long getCompetencyId() {
        return competencyId;
    }

    /**
     * @param comEvaluationId the comEvaluationId to set
     */
    public void setCompetencyId(Long competencyId) {
        this.competencyId = competencyId;
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
