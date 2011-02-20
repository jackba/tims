/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain.employeelist;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author boniface
 */
@Entity
public class CompetencyList implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String compName;
    @OneToOne
    private CompetencyType compType;
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
        if (!(object instanceof CompetencyList)) {
            return false;
        }
        CompetencyList other = (CompetencyList) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.employeelist.Competency[id=" + id + "]";
    }

    /**
     * @return the comp_name
     */
    public String getComp_name() {
        return compName;
    }

    /**
     * @param comp_name the comp_name to set
     */
    public void setComp_name(String comp_name) {
        this.compName = comp_name;
    }

    /**
     * @return the comp_type
     */
   
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
     * @return the compType
     */
    public CompetencyType getCompType() {
        return compType;
    }

    /**
     * @param compType the compType to set
     */
    public void setCompType(CompetencyType compType) {
        this.compType = compType;
    }

}
