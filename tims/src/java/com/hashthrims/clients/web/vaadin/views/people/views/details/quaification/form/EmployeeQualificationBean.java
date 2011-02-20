/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.quaification.form;

import java.util.Date;

/**
 *
 * @author boniface
 */
public class EmployeeQualificationBean {

    private Long id;
    private String typeList;
    private String courseType;
    private String comEvaluation;
    private Date lastEvaluated;
    private String competencyName;
    private String competencyNotes;

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
     * @return the typeList
     */
    public String getTypeList() {
        return typeList;
    }

    /**
     * @param typeList the typeList to set
     */
    public void setTypeList(String typeList) {
        this.typeList = typeList;
    }

    /**
     * @return the courseType
     */
    public String getCourseType() {
        return courseType;
    }

    /**
     * @param courseType the courseType to set
     */
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    /**
     * @return the comEvaluation
     */
    public String getComEvaluation() {
        return comEvaluation;
    }

    /**
     * @param comEvaluation the comEvaluation to set
     */
    public void setComEvaluation(String comEvaluation) {
        this.comEvaluation = comEvaluation;
    }

    /**
     * @return the lastEvaluated
     */
    public Date getLastEvaluated() {
        return lastEvaluated;
    }

    /**
     * @param lastEvaluated the lastEvaluated to set
     */
    public void setLastEvaluated(Date lastEvaluated) {
        this.lastEvaluated = lastEvaluated;
    }

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
}
