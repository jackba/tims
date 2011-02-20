/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class EmploymentHistoryForm implements Serializable{
    private String companyName;
    private String companyAddress;
    private String companyTelephone;
    private String supervisor;
    private String reasonForLeaving;
    private String dateSarted;
    private String startingWage;
    private String startingPosition;
    private String endingPosition;
    private String jobResponsibilities;

    private String startPosition;
    private String endPosition;
    private String responsibilities;

    /**
     * @return the companyName
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * @param companyName the companyName to set
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * @return the companyAddress
     */
    public String getCompanyAddress() {
        return companyAddress;
    }

    /**
     * @param companyAddress the companyAddress to set
     */
    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    /**
     * @return the companyTelephone
     */
    public String getCompanyTelephone() {
        return companyTelephone;
    }

    /**
     * @param companyTelephone the companyTelephone to set
     */
    public void setCompanyTelephone(String companyTelephone) {
        this.companyTelephone = companyTelephone;
    }

    /**
     * @return the supervisor
     */
    public String getSupervisor() {
        return supervisor;
    }

    /**
     * @param supervisor the supervisor to set
     */
    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    /**
     * @return the reasonForLeaving
     */
    public String getReasonForLeaving() {
        return reasonForLeaving;
    }

    /**
     * @param reasonForLeaving the reasonForLeaving to set
     */
    public void setReasonForLeaving(String reasonForLeaving) {
        this.reasonForLeaving = reasonForLeaving;
    }

    /**
     * @return the dateSarted
     */
    public String getDateSarted() {
        return dateSarted;
    }

    /**
     * @param dateSarted the dateSarted to set
     */
    public void setDateSarted(String dateSarted) {
        this.dateSarted = dateSarted;
    }

    /**
     * @return the startingWage
     */
    public String getStartingWage() {
        return startingWage;
    }

    /**
     * @param startingWage the startingWage to set
     */
    public void setStartingWage(String startingWage) {
        this.startingWage = startingWage;
    }

    /**
     * @return the startingPosition
     */
    public String getStartingPosition() {
        return startingPosition;
    }

    /**
     * @param startingPosition the startingPosition to set
     */
    public void setStartingPosition(String startingPosition) {
        this.startingPosition = startingPosition;
    }

    /**
     * @return the endingPosition
     */
    public String getEndingPosition() {
        return endingPosition;
    }

    /**
     * @param endingPosition the endingPosition to set
     */
    public void setEndingPosition(String endingPosition) {
        this.endingPosition = endingPosition;
    }

    /**
     * @return the jobResponsibilities
     */
    public String getJobResponsibilities() {
        return jobResponsibilities;
    }

    /**
     * @param jobResponsibilities the jobResponsibilities to set
     */
    public void setJobResponsibilities(String jobResponsibilities) {
        this.jobResponsibilities = jobResponsibilities;
    }

    /**
     * @return the startPosition
     */
    public String getStartPosition() {
        return startPosition;
    }

    /**
     * @param startPosition the startPosition to set
     */
    public void setStartPosition(String startPosition) {
        this.startPosition = startPosition;
    }

    /**
     * @return the endPosition
     */
    public String getEndPosition() {
        return endPosition;
    }

    /**
     * @param endPosition the endPosition to set
     */
    public void setEndPosition(String endPosition) {
        this.endPosition = endPosition;
    }

    /**
     * @return the responsibilities
     */
    public String getResponsibilities() {
        return responsibilities;
    }

    /**
     * @param responsibilities the responsibilities to set
     */
    public void setResponsibilities(String responsibilities) {
        this.responsibilities = responsibilities;
    }
}
