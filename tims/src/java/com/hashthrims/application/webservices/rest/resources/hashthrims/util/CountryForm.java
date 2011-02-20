/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class CountryForm implements Serializable{
   
  private String countryName;
    private String alphaCode;
    private int numericCode;
    private boolean primaryCountry;
    private boolean location;

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
     * @return the primaryCountry
     */
    public boolean isPrimaryCountry() {
        return primaryCountry;
    }

    /**
     * @param primaryCountry the primaryCountry to set
     */
    public void setPrimaryCountry(boolean primaryCountry) {
        this.primaryCountry = primaryCountry;
    }

    /**
     * @return the location
     */
    public boolean isLocation() {
        return location;
    }

    /**
     * @param location the location to set
     */
    public void setLocation(boolean location) {
        this.location = location;
    }

    /**
     * @return the accidentType
     */
  
   
}
