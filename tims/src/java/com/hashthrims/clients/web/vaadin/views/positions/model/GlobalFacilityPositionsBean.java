/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.model;

import java.util.List;

/**
 *
 * @author stud
 */
public class GlobalFacilityPositionsBean {

    private Long positionId;
    private List<Long>  facilities;


    /**
     * @return the positionId
     */
    public Long getPositionId() {
        return positionId;
    }

    /**
     * @param positionId the positionId to set
     */
    public void setPositionId(Long positionId) {
        this.positionId = positionId;
    }


    /**
     * @return the facilities
     */
    public List<Long> getFacilities() {
        return facilities;
    }

    /**
     * @param facilities the facilities to set
     */
    public void setFacilities(List<Long> facilities) {
        this.facilities = facilities;
    }

   

}
