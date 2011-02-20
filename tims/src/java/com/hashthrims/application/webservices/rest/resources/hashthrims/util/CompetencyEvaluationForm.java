/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class CompetencyEvaluationForm implements Serializable{
   
    private String competencyEvaluationName;

    /**
     * @return the competencyEvaluationName
     */
    public String getCompetencyEvaluationName() {
        return competencyEvaluationName;
    }

    /**
     * @param competencyEvaluationName the competencyEvaluationName to set
     */
    public void setCompetencyEvaluationName(String competencyEvaluationName) {
        this.competencyEvaluationName = competencyEvaluationName;
    }

}
