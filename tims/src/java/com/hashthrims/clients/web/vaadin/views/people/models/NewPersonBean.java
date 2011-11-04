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
    private String telephoneNumber;
    private String cellnumber;
    private String faxnumber;
    private String email;
    private String idType;
    private String idValue;
    private Long positionId;
    private Date startDate;
    private Long facilityId;
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

    public String getCellnumber() {
        return cellnumber;
    }

    public void setCellnumber(String cellnumber) {
        this.cellnumber = cellnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(Long facilityId) {
        this.facilityId = facilityId;
    }

    public String getFaxnumber() {
        return faxnumber;
    }

    public void setFaxnumber(String faxnumber) {
        this.faxnumber = faxnumber;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdValue() {
        return idValue;
    }

    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

    public Long getPositionId() {
        return positionId;
    }

    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
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
