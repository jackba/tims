/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.people.models;

/**
 *
 * @author boniface
 */
public class SearchPersonBean {
    private String firstName;
    private String lastName;
    private Long facility;

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the facility
     */
    public Long getFacility() {
        return facility;
    }

    /**
     * @param facility the facility to set
     */
    public void setFacility(Long facility) {
        this.facility = facility;
    }

    
}
