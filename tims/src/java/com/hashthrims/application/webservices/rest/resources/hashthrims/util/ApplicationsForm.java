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
public class ApplicationsForm implements Serializable{
    private boolean employmentyContract;
    private String availability;
    private String adverSource;
    private String additionalSkills;
    private String felony;
    private String felonyDetails;

    /**
     * @return the employmentyContract
     */
    public boolean isEmploymentyContract() {
        return employmentyContract;
    }

    /**
     * @param employmentyContract the employmentyContract to set
     */
    public void setEmploymentyContract(boolean employmentyContract) {
        this.employmentyContract = employmentyContract;
    }

    /**
     * @return the availability
     */
    public String getAvailability() {
        return availability;
    }

    /**
     * @param availability the availability to set
     */
    public void setAvailability(String availability) {
        this.availability = availability;
    }

    /**
     * @return the adverSource
     */
    public String getAdverSource() {
        return adverSource;
    }

    /**
     * @param adverSource the adverSource to set
     */
    public void setAdverSource(String adverSource) {
        this.adverSource = adverSource;
    }

    /**
     * @return the additionalSkills
     */
    public String getAdditionalSkills() {
        return additionalSkills;
    }

    /**
     * @param additionalSkills the additionalSkills to set
     */
    public void setAdditionalSkills(String additionalSkills) {
        this.additionalSkills = additionalSkills;
    }

    /**
     * @return the felony
     */
    public String getFelony() {
        return felony;
    }

    /**
     * @param felony the felony to set
     */
    public void setFelony(String felony) {
        this.felony = felony;
    }

    /**
     * @return the felonyDetails
     */
    public String getFelonyDetails() {
        return felonyDetails;
    }

    /**
     * @param felonyDetails the felonyDetails to set
     */
    public void setFelonyDetails(String felonyDetails) {
        this.felonyDetails = felonyDetails;
    }
}
