/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.education.form;

import java.util.Date;

/**
 *
 * @author boniface
 */
public class EducationHistoryBean {

    private Long id;
    private String instituteName;
    private Long country;
    private Date graduation;
    private Long degreeType;
    private String major;

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
     * @return the graduation
     */
    public Date getGraduation() {
        return graduation;
    }

    /**
     * @param graduation the graduation to set
     */
    public void setGraduation(Date graduation) {
        this.graduation = graduation;
    }

    /**
     * @return the major
     */
    public String getMajor() {
        return major;
    }

    /**
     * @param major the major to set
     */
    public void setMajor(String major) {
        this.major = major;
    }

    /**
     * @return the instituteName
     */
    public String getInstituteName() {
        return instituteName;
    }

    /**
     * @param instituteName the instituteName to set
     */
    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    /**
     * @return the country
     */
    public Long getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(Long country) {
        this.country = country;
    }

    /**
     * @return the degreeType
     */
    public Long getDegreeType() {
        return degreeType;
    }

    /**
     * @param degreeType the degreeType to set
     */
    public void setDegreeType(Long degreeType) {
        this.degreeType = degreeType;
    }

   
}
