/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.location.model;

/**
 *
 * @author boniface
 */
public class AddressTypeBean {
    private Long addressTypeId;
    private String addressTypeName;

    /**
     * @return the addressTypeId
     */
    public Long getAddressTypeId() {
        return addressTypeId;
    }

    /**
     * @param addressTypeId the addressTypeId to set
     */
    public void setAddressTypeId(Long addressTypeId) {
        this.addressTypeId = addressTypeId;
    }

    /**
     * @return the addressTypeName
     */
    public String getAddressTypeName() {
        return addressTypeName;
    }

    /**
     * @param addressTypeName the addressTypeName to set
     */
    public void setAddressTypeName(String addressTypeName) {
        this.addressTypeName = addressTypeName;
    }
   
}
