/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.employeelists.qualifications.model;

/**
 *
 * @author boniface
 */
public class DegreeBean {
    private Long degrId;
    private String degreeName;
    private String educType;

    /**
     * @return the degrId
     */
    public Long getDegrId() {
        return degrId;
    }

    /**
     * @param degrId the degrId to set
     */
    public void setDegrId(Long degrId) {
        this.degrId = degrId;
    }

    /**
     * @return the degreeName
     */
    public String getDegreeName() {
        return degreeName;
    }

    /**
     * @param degreeName the degreeName to set
     */
    public void setDegreeName(String degreeName) {
        this.degreeName = degreeName;
    }

    /**
     * @return the educType
     */
    public String getEducType() {
        return educType;
    }

    /**
     * @param educType the educType to set
     */
    public void setEducType(String educType) {
        this.educType = educType;
    }
    
}
