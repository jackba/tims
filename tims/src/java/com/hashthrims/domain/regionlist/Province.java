/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.regionlist;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author boniface
 */
@Entity
public class Province implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String regionName;
    private String regionCode;
    @OneToOne
    private Country country;
    @JoinColumn(name = "province_id")
    @OneToMany(orphanRemoval = true, cascade = { javax.persistence.CascadeType.ALL })
    private List<County> county;

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
        if (!(object instanceof Province)) {
            return false;
        }
        Province other = (Province) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.region.Region[id=" + id + "]";
    }

    /**
     * @return the regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName the regionName to set
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * @return the regionCode
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * @param regionCode the regionCode to set
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }



    /**
     * @return the county
     */
    public List<County> getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(List<County> county) {
        this.county = county;
    }

    /**
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Country country) {
        this.country = country;
    }


}
