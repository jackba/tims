/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author boniface
 */
@Entity
public class EmploymentHistory implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String companyName;
    private String companyAddress;
    private String companyTelephone;
    private String supervisor;
    private String reasonForLeaving;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateSarted;

    private String startingPosition;

    private String endingPosition;
    private String jobResponsibilities;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EmploymentHistory)) {
            return false;
        }
        EmploymentHistory other = (EmploymentHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.EmploymentHistory[id=" + id + "]";
    }

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

   
   
}
