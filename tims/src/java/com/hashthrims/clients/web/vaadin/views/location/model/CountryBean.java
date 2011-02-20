/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.location.model;

/**
 *
 * @author boniface
 */
public class CountryBean {
    private String countryName;
    private String alphaCode;
    private int numericCode;
    private Long countryId;

    /**
     * @return the countryName
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the countryName to set
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the alphaCode
     */
    public String getAlphaCode() {
        return alphaCode;
    }

    /**
     * @param alphaCode the alphaCode to set
     */
    public void setAlphaCode(String alphaCode) {
        this.alphaCode = alphaCode;
    }

    /**
     * @return the numericCode
     */
    public int getNumericCode() {
        return numericCode;
    }

    /**
     * @param numericCode the numericCode to set
     */
    public void setNumericCode(int numericCode) {
        this.numericCode = numericCode;
    }

    /**
     * @return the countryId
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the countryId to set
     */
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

}
