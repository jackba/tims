/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain;

import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
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
public class DisciplinaryAction implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private DisciplineActionTypeList empDisciplinaryAction;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date dateOfDiscussion;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date SatrtofAplicability;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date endofAplicability;
    private String peoplePresent;
    private String notes;

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
        if (!(object instanceof DisciplinaryAction)) {
            return false;
        }
        DisciplinaryAction other = (DisciplinaryAction) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.DisciplinaryAction[id=" + id + "]";
    }

    /**
     * @return the empDisciplinaryAction
     */
    public DisciplineActionTypeList getEmpDisciplinaryAction() {
        return empDisciplinaryAction;
    }

    /**
     * @param empDisciplinaryAction the empDisciplinaryAction to set
     */
    public void setEmpDisciplinaryAction(DisciplineActionTypeList empDisciplinaryAction) {
        this.empDisciplinaryAction = empDisciplinaryAction;
    }

    /**
     * @return the dateOfDiscussion
     */
    public Date getDateOfDiscussion() {
        return dateOfDiscussion;
    }

    /**
     * @param dateOfDiscussion the dateOfDiscussion to set
     */
    public void setDateOfDiscussion(Date dateOfDiscussion) {
        this.dateOfDiscussion = dateOfDiscussion;
    }

    /**
     * @return the SatrtofAplicability
     */
    public Date getSatrtofAplicability() {
        return SatrtofAplicability;
    }

    /**
     * @param SatrtofAplicability the SatrtofAplicability to set
     */
    public void setSatrtofAplicability(Date SatrtofAplicability) {
        this.SatrtofAplicability = SatrtofAplicability;
    }

    /**
     * @return the endofAplicability
     */
    public Date getEndofAplicability() {
        return endofAplicability;
    }

    /**
     * @param endofAplicability the endofAplicability to set
     */
    public void setEndofAplicability(Date endofAplicability) {
        this.endofAplicability = endofAplicability;
    }

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
