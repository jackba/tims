/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.models;

import java.util.Date;
import java.util.List;

/**
 *
 * @author boniface
 */
public class NewPersonBean {
    private Long id;
    private String firstname;
    private String othername;
    private String surname;
    private Date dob;
    private Long raceid;
    private Long genderId;
    private List<Long> rolesId;
    private List<Long> expertiseId;
    private List<Long> competencyFieldId;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the firstname
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * @param firstname the firstname to set
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * @return the othername
     */
    public String getOthername() {
        return othername;
    }

    /**
     * @param othername the othername to set
     */
    public void setOthername(String othername) {
        this.othername = othername;
    }

    /**
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * @param surname the surname to set
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * @return the dob
     */
    public Date getDob() {
        return dob;
    }

    /**
     * @param dob the dob to set
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }

    /**
     * @return the raceid
     */
    public Long getRaceid() {
        return raceid;
    }

    /**
     * @param raceid the raceid to set
     */
    public void setRaceid(Long raceid) {
        this.raceid = raceid;
    }

    /**
     * @return the genderId
     */
    public Long getGenderId() {
        return genderId;
    }

    /**
     * @param genderId the genderId to set
     */
    public void setGenderId(Long genderId) {
        this.genderId = genderId;
    }

    /**
     * @return the rolesId
     */
    public List<Long> getRolesId() {
        return rolesId;
    }

    /**
     * @param rolesId the rolesId to set
     */
    public void setRolesId(List<Long> rolesId) {
        this.rolesId = rolesId;
    }

    /**
     * @return the expertiseId
     */
    public List<Long> getExpertiseId() {
        return expertiseId;
    }

    /**
     * @param expertiseId the expertiseId to set
     */
    public void setExpertiseId(List<Long> expertiseId) {
        this.expertiseId = expertiseId;
    }

    /**
     * @return the competencyFieldId
     */
    public List<Long> getCompetencyFieldId() {
        return competencyFieldId;
    }

    /**
     * @param competencyFieldId the competencyFieldId to set
     */
    public void setCompetencyFieldId(List<Long> competencyFieldId) {
        this.competencyFieldId = competencyFieldId;
    }
    
}
