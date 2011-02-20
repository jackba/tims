/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.models;

import java.io.Serializable;

/**
 *
 * @author boniface
 */
public class PersonalInformationBean implements Serializable{
    private String surname;
    private String firstname;
    private String othernames;
    private String nationality;
    private String residency;

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
     * @return the othernames
     */
    public String getOthernames() {
        return othernames;
    }

    /**
     * @param othernames the othernames to set
     */
    public void setOthernames(String othernames) {
        this.othernames = othernames;
    }

    /**
     * @return the nationality
     */
    public String getNationality() {
        return nationality;
    }

    /**
     * @param nationalisty the nationality to set
     */
    public void setNationality(String nationalisty) {
        this.nationality = nationality;
    }

    /**
     * @return the residency
     */
    public String getResidency() {
        return residency;
    }

    /**
     * @param residency the residency to set
     */
    public void setResidency(String residency) {
        this.residency = residency;
    }

   

}
