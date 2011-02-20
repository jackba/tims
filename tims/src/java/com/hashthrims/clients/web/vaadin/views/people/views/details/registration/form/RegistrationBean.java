/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.registration.form;

import java.util.Date;

/**
 *
 * @author boniface
 */
public class RegistrationBean {

    private Long id;
    private Long registrationBody;
    private String registrationNumber;
    private Date registrationDate;
    private String licenceNumber;
    private Date expirationDate;

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
     * @return the registrationNumber
     */
    public String getRegistrationNumber() {
        return registrationNumber;
    }

    /**
     * @param registrationNumber the registrationNumber to set
     */
    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    /**
     * @return the registrationDate
     */
    public Date getRegistrationDate() {
        return registrationDate;
    }

    /**
     * @param registrationDate the registrationDate to set
     */
    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    /**
     * @return the licenceNumber
     */
    public String getLicenceNumber() {
        return licenceNumber;
    }

    /**
     * @param licenceNumber the licenceNumber to set
     */
    public void setLicenceNumber(String licenceNumber) {
        this.licenceNumber = licenceNumber;
    }

    /**
     * @return the expirationDate
     */
    public Date getExpirationDate() {
        return expirationDate;
    }

    /**
     * @param expirationDate the expirationDate to set
     */
    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    /**
     * @return the registrationBody
     */
    public Long getRegistrationBody() {
        return registrationBody;
    }

    /**
     * @param registrationBody the registrationBody to set
     */
    public void setRegistrationBody(Long registrationBody) {
        this.registrationBody = registrationBody;
    }
}
