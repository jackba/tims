/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.people.views.details.contacts.form;

import java.io.Serializable;

/**
 *
 * @author boniface
 */
public class ContactsBean implements Serializable{
    private Long id;
    private String mailingAddress;
    private String telephoneNumber;
    private String cellnumber;
    private String faxnumber;
    private String email;
    private String notes;
    private String addressType;

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
     * @return the mailingAddress
     */
    public String getMailingAddress() {
        return mailingAddress;
    }

    /**
     * @param mailingAddress the mailingAddress to set
     */
    public void setMailingAddress(String mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    /**
     * @return the telephoneNumber
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * @param telephoneNumber the telephoneNumber to set
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * @return the cellnumber
     */
    public String getCellnumber() {
        return cellnumber;
    }

    /**
     * @param cellnumber the cellnumber to set
     */
    public void setCellnumber(String cellnumber) {
        this.cellnumber = cellnumber;
    }

    /**
     * @return the faxnumber
     */
    public String getFaxnumber() {
        return faxnumber;
    }

    /**
     * @param faxnumber the faxnumber to set
     */
    public void setFaxnumber(String faxnumber) {
        this.faxnumber = faxnumber;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the addressType
     */
    public String getAddressType() {
        return addressType;
    }

    /**
     * @param addressType the addressType to set
     */
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

}
