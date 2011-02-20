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
public class CompetencyTypeForm implements Serializable{
 
    private String competencyType;

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

    /**
     * @return the employmentyContract
     */
   
}
