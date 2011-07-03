/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.traininglist;

import com.hashthrims.domain.Contacts;
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
public class TrainingInstitution implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String trainingInstitution;
    @OneToOne
    private City city;
    @OneToOne(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    private Contacts contact;
    @OneToMany(orphanRemoval = true, cascade = {javax.persistence.CascadeType.ALL})
    @JoinColumn(name = "training_id")
    private List<OrganisationTrainers> organisationTrainers = new ArrayList<OrganisationTrainers>();

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
        if (!(object instanceof TrainingInstitution)) {
            return false;
        }
        TrainingInstitution other = (TrainingInstitution) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.traininglist.TrainingInstitution[id=" + id + "]";
    }

    /**
     * @return the trainingInstitution
     */
    public String getTrainingInstitution() {
        return trainingInstitution;
    }

    /**
     * @param trainingInstitution the trainingInstitution to set
     */
    public void setTrainingInstitution(String trainingInstitution) {
        this.trainingInstitution = trainingInstitution;
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
     * @return the organisationTrainers
     */
    public List<OrganisationTrainers> getOrganisationTrainers() {
        return organisationTrainers;
    }

    /**
     * @param organisationTrainers the organisationTrainers to set
     */
    public void setOrganisationTrainers(List<OrganisationTrainers> organisationTrainers) {
        this.organisationTrainers = organisationTrainers;
    }
}
