/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.employeelist;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author boniface
 */
@Entity
public class CompetencyType implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String compNameType;
    @JoinColumn(name = "comptype_id")
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    private List<CompetencyList> competencyList;

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
        if (!(object instanceof CompetencyType)) {
            return false;
        }
        CompetencyType other = (CompetencyType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.employeelist.CompetencyType[id=" + id + "]";
    }

    /**
     * @return the comp_name_typ
     */
    public String getComp_name_typ() {
        return compNameType;
    }

    /**
     * @param comp_name_typ the comp_name_typ to set
     */
    public void setComp_name_typ(String comp_name_typ) {
        this.compNameType = comp_name_typ;
    }

    /**
     * @return the competencyList
     */
    public List<CompetencyList> getCompetencyList() {
        return competencyList;
    }

    /**
     * @param competencyList the competencyList to set
     */
    public void setCompetencyList(List<CompetencyList> competencyList) {
        this.competencyList = competencyList;
    }

    
}
