/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain.regionlist;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author boniface
 */
@Entity
public class Country implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String countryName;
    private String alphaCode;
    private int numericCode;
    private boolean primaryCountry;
    private boolean location;
    @OneToMany(orphanRemoval = true, cascade = { javax.persistence.CascadeType.ALL })
    @JoinColumn(name = "country_id")
    private List<Province> province=new ArrayList<Province>();
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
        if (!(object instanceof Country)) {
            return false;
        }
        Country other = (Country) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.region.Country[id=" + id + "]";
    }

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the alphaCode
     */
    public String getAlphaCode() {
        return alphaCode;
    }

    /**
     * @param alphaCode the alphaCode to set
     */
    public void setAlphaCode(String alphaCode) {
        this.alphaCode = alphaCode;
    }

    /**
     * @return the numericCode
     */
    public int getNumericCode() {
        return numericCode;
    }

    /**
     * @param numericCode the numericCode to set
     */
    public void setNumericCode(int numericCode) {
        this.numericCode = numericCode;
    }


    /**
     * @return the primaryCountry
     */
    public boolean isPrimaryCountry() {
        return primaryCountry;
    }

    /**
     * @param primaryCountry the primaryCountry to set
     */
    public void setPrimaryCountry(boolean primaryCountry) {
        this.primaryCountry = primaryCountry;
    }

    /**
     * @return the location
     */
    public boolean isLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(boolean location) {
        this.location = location;
    }

    /**
     * @return the province
     */
    public List<Province> getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(List<Province> province) {
        this.province = province;
    }

}
