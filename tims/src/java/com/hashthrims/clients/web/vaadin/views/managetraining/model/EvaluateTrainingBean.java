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
public class EvaluateTrainingBean {
    private Long id;
    //Dates
    private Date evaluationDate;
    private Long evaluation;
    private Long facultyId;
    private List<Long> attendees;


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
     * @return the evaluationDate
     */
    public Date getEvaluationDate() {
        return evaluationDate;
    }

    /**
     * @param evaluationDate the evaluationDate to set
     */
    public void setEvaluationDate(Date evaluationDate) {
        this.evaluationDate = evaluationDate;
    }

    /**
     * @return the evaluation
     */
    public Long getEvaluation() {
        return evaluation;
    }

    /**
     * @param evaluation the evaluation to set
     */
    public void setEvaluation(Long evaluation) {
        this.evaluation = evaluation;
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
     * @return the attendees
     */
    public List<Long> getAttendees() {
        return attendees;
    }

    /**
     * @param attendees the attendees to set
     */
    public void setAttendees(List<Long> attendees) {
        this.attendees = attendees;
    }

    
   

}
