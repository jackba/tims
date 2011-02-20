/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain;

import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.domain.positions.Positions;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author boniface
 */
@Entity
public class Applications implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private Positions position;

    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @OneToOne
    private SalaryGrade desiredWage;
    private boolean employmentyContract;
    private String availability;
    private String adverSource;
    private String additionalSkills;
    private String felony;
    private String felonyDetails;

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
        if (!(object instanceof Applications)) {
            return false;
        }
        Applications other = (Applications) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.Applications[id=" + id + "]";
    }

    /**
     * @return the position
     */
    public Positions getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Positions position) {
        this.position = position;
    }

    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

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

    /**
     * @return the desiredWage
     */
    public SalaryGrade getDesiredWage() {
        return desiredWage;
    }

    /**
     * @param desiredWage the desiredWage to set
     */
    public void setDesiredWage(SalaryGrade desiredWage) {
        this.desiredWage = desiredWage;
    }


  
}
