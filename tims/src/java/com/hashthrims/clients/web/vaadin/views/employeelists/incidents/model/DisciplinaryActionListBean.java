/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.employeelists.incidents.model;

/**
 *
 * @author boniface
 */
public class DisciplinaryActionListBean {

    private Long disciplinaryTypeListId;
    private String displineName;

    /**
     * @return the disciplinaryTypeListId
     */
    public Long getDisciplinaryTypeListId() {
        return disciplinaryTypeListId;
    }

    /**
     * @param disciplinaryTypeListId the disciplinaryTypeListId to set
     */
    public void setDisciplinaryTypeListId(Long disciplinaryTypeListId) {
        this.disciplinaryTypeListId = disciplinaryTypeListId;
    }

    /**
     * @return the displineName
     */
    public String getDisplineName() {
        return displineName;
    }

    /**
     * @param displineName the displineName to set
     */
    public void setDisplineName(String displineName) {
        this.displineName = displineName;
    }
}
