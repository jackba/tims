/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.jobs.model;

/**
 *
 * @author boniface
 */
public class JobClassificationBean {
    private Long classificationId;
    private String name;
    private String description;
    private String code;

    /**
     * @return the classificationId
     */
    public Long getClassificationId() {
        return classificationId;
    }

    /**
     * @param classificationId the classificationId to set
     */
    public void setClassificationId(Long classificationId) {
        this.classificationId = classificationId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

}
