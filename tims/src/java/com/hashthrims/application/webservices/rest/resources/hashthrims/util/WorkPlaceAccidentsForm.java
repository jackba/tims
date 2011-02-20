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
public class WorkPlaceAccidentsForm implements Serializable{
private String peopleInvolved;
    private String followUpRequired;

    /**
     * @return the peopleInvolved
     */
    public String getPeopleInvolved() {
        return peopleInvolved;
    }

    /**
     * @param peopleInvolved the peopleInvolved to set
     */
    public void setPeopleInvolved(String peopleInvolved) {
        this.peopleInvolved = peopleInvolved;
    }

    /**
     * @return the followUpRequired
     */
    public String getFollowUpRequired() {
        return followUpRequired;
    }

    /**
     * @param followUpRequired the followUpRequired to set
     */
    public void setFollowUpRequired(String followUpRequired) {
        this.followUpRequired = followUpRequired;
    }
}
