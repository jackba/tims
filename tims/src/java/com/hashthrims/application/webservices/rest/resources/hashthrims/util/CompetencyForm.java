/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import java.io.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author administrator.dat
 */
@XmlRootElement
public class CompetencyForm implements Serializable{
private String competencyName;
    private String competencyNotes;

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
