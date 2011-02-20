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

/**
 *
 * @author boniface
 */
@Entity
public class BenefitType implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String benefitTypeName;
    

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
        if (!(object instanceof BenefitType)) {
            return false;
        }
        BenefitType other = (BenefitType) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.employeelist.BenefitType[id=" + id + "]";
    }

    /**
     * @return the benefit_type_name
     */
    public String getBenefit_type_name() {
        return benefitTypeName;
    }

    /**
     * @param benefit_type_name the benefit_type_name to set
     */
    public void setBenefit_type_name(String benefit_type_name) {
        this.benefitTypeName = benefit_type_name;
    }

    

}
