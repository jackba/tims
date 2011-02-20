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
public class TrainingForm implements Serializable{
private String notes;
    private boolean retraining;
    private boolean competency;

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the retraining
     */
    public boolean isRetraining() {
        return retraining;
    }

    /**
     * @param retraining the retraining to set
     */
    public void setRetraining(boolean retraining) {
        this.retraining = retraining;
    }

    /**
     * @return the competency
     */
    public boolean isCompetency() {
        return competency;
    }

    /**
     * @param competency the competency to set
     */
    public void setCompetency(boolean competency) {
        this.competency = competency;
    }
}
