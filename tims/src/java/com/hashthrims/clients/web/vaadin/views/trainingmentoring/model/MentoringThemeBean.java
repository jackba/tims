/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.trainingmentoring.model;

/**
 *
 * @author boniface
 */
public class MentoringThemeBean {

    private Long id;
    private String mentoringTheme;
    private String mentoringField;

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
     * @return the mentoringTheme
     */
    public String getMentoringTheme() {
        return mentoringTheme;
    }

    /**
     * @param mentoringTheme the mentoringTheme to set
     */
    public void setMentoringTheme(String mentoringTheme) {
        this.mentoringTheme = mentoringTheme;
    }

    /**
     * @return the mentoringField
     */
    public String getMentoringField() {
        return mentoringField;
    }

    /**
     * @param mentoringField the mentoringField to set
     */
    public void setMentoringField(String mentoringField) {
        this.mentoringField = mentoringField;
    }
}
