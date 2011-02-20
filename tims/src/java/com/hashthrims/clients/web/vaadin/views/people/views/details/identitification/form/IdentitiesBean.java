/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.people.views.details.identitification.form;

import java.io.Serializable;

/**
 *
 * @author boniface
 */
public class IdentitiesBean implements Serializable{
    private Long id;
    private String idType;
    private String idValue;

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
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the idValue
     */
    public String getIdValue() {
        return idValue;
    }

    /**
     * @param idValue the idValue to set
     */
    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }

   
}
