/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import javax.persistence.OneToOne;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class TrainingCourseEvaluationForm implements Serializable{
    
    private String evaluationName;
    @OneToOne
    private CompetencyEvaluation competeEvaluation;

    /**
     * @return the evaluationName
     */
    public String getEvaluationName() {
        return evaluationName;
    }

    /**
     * @param evaluationName the evaluationName to set
     */
    public void setEvaluationName(String evaluationName) {
        this.evaluationName = evaluationName;
    }

    /**
     * @return the competeEvaluation
     */
    public CompetencyEvaluation getCompeteEvaluation() {
        return competeEvaluation;
    }

    /**
     * @param competeEvaluation the competeEvaluation to set
     */
    public void setCompeteEvaluation(CompetencyEvaluation competeEvaluation) {
        this.competeEvaluation = competeEvaluation;
    }

}
