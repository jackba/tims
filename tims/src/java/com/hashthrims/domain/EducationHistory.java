/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain;

import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.domain.regionlist.Country;
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
public class EducationHistory implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String instituteNamwe;
    @OneToOne
    private Country Location;
    @Temporal(value = javax.persistence.TemporalType.DATE)
    private Date graduation;
    @OneToOne
    private Degree degreeType;
    private String major;

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
        if (!(object instanceof EducationHistory)) {
            return false;
        }
        EducationHistory other = (EducationHistory) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.EducationHistory[id=" + id + "]";
    }

    /**
     * @return the instituteNamwe
     */
    public String getInstituteNamwe() {
        return instituteNamwe;
    }

    /**
     * @param instituteNamwe the instituteNamwe to set
     */
    public void setInstituteNamwe(String instituteNamwe) {
        this.instituteNamwe = instituteNamwe;
    }

    /**
     * @return the Location
     */
    public Country getLocation() {
        return Location;
    }

    /**
     * @param Location the Location to set
     */
    public void setLocation(Country Location) {
        this.Location = Location;
    }

    /**
     * @return the graduation
     */
    public Date getGraduation() {
        return graduation;
    }

    /**
     * @param graduation the graduation to set
     */
    public void setGraduation(Date graduation) {
        this.graduation = graduation;
    }

    /**
     * @return the degreeType
     */
    public Degree getDegreeType() {
        return degreeType;
    }

    /**
     * @param degreeType the degreeType to set
     */
    public void setDegreeType(Degree degreeType) {
        this.degreeType = degreeType;
    }

    /**
     * @return the major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }
}
