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
public class EvaluateddCourseBean implements Serializable {

    private Long id;
    //Dates
    private Date evaluationDate;
    private Long evaluation;

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
}
