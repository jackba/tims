/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.languages.form;

import java.io.Serializable;

/**
 *
 * @author boniface
 */
public class LanguagesBean implements Serializable{

    private Long id;
    private Long language;
    private String writing;
    private String reading;
    private String speaking;

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
     * @return the language
     */
    public Long getLanguage() {
        return language;
    }

    /**
     * @param language the language to set
     */
    public void setLanguage(Long language) {
        this.language = language;
    }

    /**
     * @return the writing
     */
    public String getWriting() {
        return writing;
    }

    /**
     * @param writing the writing to set
     */
    public void setWriting(String writing) {
        this.writing = writing;
    }

    /**
     * @return the reading
     */
    public String getReading() {
        return reading;
    }

    /**
     * @param reading the reading to set
     */
    public void setReading(String reading) {
        this.reading = reading;
    }

    /**
     * @return the speaking
     */
    public String getSpeaking() {
        return speaking;
    }

    /**
     * @param speaking the speaking to set
     */
    public void setSpeaking(String speaking) {
        this.speaking = speaking;
    }
}
