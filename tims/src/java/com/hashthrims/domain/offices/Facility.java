/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.offices;

import com.hashthrims.domain.Contacts;
import com.hashthrims.domain.positions.Positions;
import com.hashthrims.domain.regionlist.City;
import java.io.Serializable;
import java.util.ArrayList;
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
public class Facility implements Serializable, Comparable<Facility> {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String facilityName;
    @OneToOne
    private FacilityType facilityType;
    @OneToOne
    private City city;
    @OneToOne(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    private Contacts contact;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "facililty_id")
    private List<Positions> positions = new ArrayList<Positions>();
    
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "facililty_id")
    private List<FacilityMentors> facilityMentors = new ArrayList<FacilityMentors>();
    
    @OneToOne(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "facililty_id")
    private FacilityGrouping facilityGrouping;

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
        if (!(object instanceof Facility)) {
            return false;
        }
        Facility other = (Facility) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.offices.Facility[id=" + id + "]";
    }

    /**
     * @return the facilityType
     */
    public FacilityType getFacilityType() {
        return facilityType;
    }

    /**
     * @param facilityType the facilityType to set
     */
    public void setFacilityType(FacilityType facilityType) {
        this.facilityType = facilityType;
    }

    /**
     * @return the contact
     */
    public Contacts getContact() {
        return contact;
    }

    /**
     * @param contact the contact to set
     */
    public void setContact(Contacts contact) {
        this.contact = contact;
    }

    /**
     * @return the facilityName
     */
    public String getFacilityName() {
        return facilityName;
    }

    /**
     * @param facilityName the facilityName to set
     */
    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    /**
     * @return the city
     */
    public City getCity() {
        return city;
    }

    /**
     * @param city the city to set
     */
    public void setCity(City city) {
        this.city = city;
    }

    /**
     * @return the positions
     */
    public List<Positions> getPositions() {
        return positions;
    }

    /**
     * @param positions the positions to set
     */
    public void setPositions(List<Positions> positions) {
        this.positions = positions;
    }

    @Override
    public int compareTo(Facility o) {
        return facilityName.compareTo(o.facilityName);
    }

    /**
     * @return the facilityMentors
     */
    public List<FacilityMentors> getFacilityMentors() {
        return facilityMentors;
    }

    /**
     * @param facilityMentors the facilityMentors to set
     */
    public void setFacilityMentors(List<FacilityMentors> facilityMentors) {
        this.facilityMentors = facilityMentors;
    }

    /**
     * @return the facilityGrouping
     */
    public FacilityGrouping getFacilityGrouping() {
        return facilityGrouping;
    }

    /**
     * @param facilityGrouping the facilityGrouping to set
     */
    public void setFacilityGrouping(FacilityGrouping facilityGrouping) {
        this.facilityGrouping = facilityGrouping;
    }
}
