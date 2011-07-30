/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managementoring.model;

import java.util.Set;

/**
 *
 * @author boniface
 */
public class EnrollMenteesBean {
    private Long id;
    private Set<Long> menteesId;
    private Long mentoringSessionId;

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
     * @return the mentoringSessionId
     */
    public Long getMentoringSessionId() {
        return mentoringSessionId;
    }

    /**
     * @param mentoringSessionId the mentoringSessionId to set
     */
    public void setMentoringSessionId(Long mentoringSessionId) {
        this.mentoringSessionId = mentoringSessionId;
    }

    /**
     * @return the menteesId
     */
    public Set<Long> getMenteesId() {
        return menteesId;
    }

    /**
     * @param menteesId the menteesId to set
     */
    public void setMenteesId(Set<Long> menteesId) {
        this.menteesId = menteesId;
    }
    
}
