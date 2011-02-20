/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.employeelists.incidents.model;

/**
 *
 * @author boniface
 */
public class DepartureReasonsBean {

    private Long depId;
    private String reasonName; 

    /**
     * @return the reasonName
     */
    public String getReasonName() {
        return reasonName;
    }

    /**
     * @param reasonName the reasonName to set
     */
    public void setReasonName(String reasonName) {
        this.reasonName = reasonName;
    }

    /**
     * @return the depId
     */
    public Long getDepId() {
        return depId;
    }

    /**
     * @param depId the depId to set
     */
    public void setDepId(Long depId) {
        this.depId = depId;
    }

}
