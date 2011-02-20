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
public class Degree implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String degreeName;
    @OneToOne
    private EducationType educType;
    


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
        if (!(object instanceof Degree)) {
            return false;
        }
        Degree other = (Degree) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.employeelist.Degree[id=" + id + "]";
    }

    /**
     * @return the degree_name
     */
    public String getDegree_name() {
        return degreeName;
    }

    /**
     * @param degree_name the degree_name to set
     */
    public void setDegree_name(String degree_name) {
        this.degreeName = degree_name;
    }

    /**
     * @return the educType
     */
    public EducationType getEducType() {
        return educType;
    }

    /**
     * @param educType the educType to set
     */
    public void setEducType(EducationType educType) {
        this.educType = educType;
    }

   
}
