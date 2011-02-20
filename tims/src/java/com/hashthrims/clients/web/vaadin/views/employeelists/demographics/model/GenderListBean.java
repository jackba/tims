/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.employeelists.demographics.model;

import java.io.Serializable;

/**
 *
 * @author boniface
 */
public class GenderListBean implements Serializable{
    private Long genderId;
    private String genderListName;

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
     * @return the genderListName
     */
    public String getGenderListName() {
        return genderListName;
    }

    /**
     * @param genderListName the genderListName to set
     */
    public void setGenderListName(String genderListName) {
        this.genderListName = genderListName;
    }

    

}
