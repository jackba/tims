/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain;

import com.hashthrims.domain.offices.Facility;
import com.hashthrims.domain.traininglist.MentoringSession;
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
public class EmployeeMentoring implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date mentoringDate;
    private Long patientsInitiated;
    private Long hoursSpent;
    @OneToOne
    private MentoringSession mentoringSession;
    @OneToOne
    private Facility venue;

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
        if (!(object instanceof EmployeeMentoring)) {
            return false;
        }
        EmployeeMentoring other = (EmployeeMentoring) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.Training[id=" + id + "]";
    }

    
    /**
     * @return the mentoringSession
     */
    public MentoringSession getMentoringSession() {
        return mentoringSession;
    }

    /**
     * @param mentoringSession the mentoringSession to set
     */
    public void setMentoringSession(MentoringSession mentoringSession) {
        this.mentoringSession = mentoringSession;
    }

    /**
     * @return the mentoringDate
     */
    public Date getMentoringDate() {
        return mentoringDate;
    }

    /**
     * @param mentoringDate the mentoringDate to set
     */
    public void setMentoringDate(Date mentoringDate) {
        this.mentoringDate = mentoringDate;
    }

    /**
     * @return the patientsInitiated
     */
    public Long getPatientsInitiated() {
        return patientsInitiated;
    }

    /**
     * @param patientsInitiated the patientsInitiated to set
     */
    public void setPatientsInitiated(Long patientsInitiated) {
        this.patientsInitiated = patientsInitiated;
    }

    /**
     * @return the hoursSpent
     */
    public Long getHoursSpent() {
        return hoursSpent;
    }

    /**
     * @param hoursSpent the hoursSpent to set
     */
    public void setHoursSpent(Long hoursSpent) {
        this.hoursSpent = hoursSpent;
    }

    /**
     * @return the venue
     */
    public Facility getVenue() {
        return venue;
    }

    /**
     * @param venue the venue to set
     */
    public void setVenue(Facility venue) {
        this.venue = venue;
    }
}
