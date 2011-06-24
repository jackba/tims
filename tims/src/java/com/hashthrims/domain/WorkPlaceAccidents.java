/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain;

import com.hashthrims.domain.employeelist.AccidentTypeList;
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
public class WorkPlaceAccidents implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private AccidentTypeList accidentType;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date Occurence;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date stratAplicability;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date endAplicability;
    private String peopleInvioved;
    private String followuopRequired;

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
        if (!(object instanceof WorkPlaceAccidents)) {
            return false;
        }
        WorkPlaceAccidents other = (WorkPlaceAccidents) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.WorkplaceIncidents[id=" + id + "]";
    }

    /**
     * @return the accidentType
     */
    public AccidentTypeList getAccidentType() {
        return accidentType;
    }

    /**
     * @param accidentType the accidentType to set
     */
    public void setAccidentType(AccidentTypeList accidentType) {
        this.accidentType = accidentType;
    }

    /**
     * @return the Occurence
     */
    public Date getOccurence() {
        return Occurence;
    }

    /**
     * @param Occurence the Occurence to set
     */
    public void setOccurence(Date Occurence) {
        this.Occurence = Occurence;
    }

    /**
     * @return the stratAplicability
     */
    public Date getStratAplicability() {
        return stratAplicability;
    }

    /**
     * @param stratAplicability the stratAplicability to set
     */
    public void setStratAplicability(Date stratAplicability) {
        this.stratAplicability = stratAplicability;
    }

    /**
     * @return the endAplicability
     */
    public Date getEndAplicability() {
        return endAplicability;
    }

    /**
     * @param endAplicability the endAplicability to set
     */
    public void setEndAplicability(Date endAplicability) {
        this.endAplicability = endAplicability;
    }

    /**
     * @return the peopleInvioved
     */
    public String getPeopleInvioved() {
        return peopleInvioved;
    }

    /**
     * @param peopleInvioved the peopleInvioved to set
     */
    public void setPeopleInvioved(String peopleInvioved) {
        this.peopleInvioved = peopleInvioved;
    }

    /**
     * @return the followuopRequired
     */
    public String getFollowuopRequired() {
        return followuopRequired;
    }

    /**
     * @param followuopRequired the followuopRequired to set
     */
    public void setFollowuopRequired(String followuopRequired) {
        this.followuopRequired = followuopRequired;
    }
}
