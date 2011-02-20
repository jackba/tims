/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.facilites.model;

/**
 *
 * @author boniface
 */
public class RegistrationBodyBean {
    private String registrationBodyName;
    private long registrationBodyId;

   
    public long getRegistrationBodyId() {
        return registrationBodyId;
    }

    /**
     * @param registrationBodyId the registrationBodyId to set
     */
    public void setRegistrationBodyId(long registrationBodyId) {
        this.registrationBodyId = registrationBodyId;
    }

    /**
     * @return the registrationBodyName
     */
    public String getRegistrationBodyName() {
        return registrationBodyName;
    }

    /**
     * @param registrationBodyName the registrationBodyName to set
     */
    public void setRegistrationBodyName(String registrationBodyName) {
        this.registrationBodyName = registrationBodyName;
    }

  
  
}
