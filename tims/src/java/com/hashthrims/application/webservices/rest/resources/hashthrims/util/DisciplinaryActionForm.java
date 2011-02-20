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
public class DisciplinaryActionForm implements Serializable{
private String peoplePresent;
    private String notes;

    /**
     * @return the peoplePresent
     */
    public String getPeoplePresent() {
        return peoplePresent;
    }

    /**
     * @param peoplePresent the peoplePresent to set
     */
    public void setPeoplePresent(String peoplePresent) {
        this.peoplePresent = peoplePresent;
    }

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
}
