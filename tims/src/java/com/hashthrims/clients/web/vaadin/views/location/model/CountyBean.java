/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.location.model;

/**
 *
 * @author boniface
 */
public class CountyBean {

    private String countyName;
    private String countyCode;
    private String province;
    private Long countyId;

    /**
     * @return the countyName
     */
    public String getCountyName() {
        return countyName;
    }

    /**
     * @param countyName the countyName to set
     */
    public void setCountyName(String countyName) {
        this.countyName = countyName;
    }

    /**
     * @return the countyCode
     */
    public String getCountyCode() {
        return countyCode;
    }

    /**
     * @param countyCode the countyCode to set
     */
    public void setCountyCode(String countyCode) {
        this.countyCode = countyCode;
    }

    /**
     * @return the province
     */
    public String getProvince() {
        return province;
    }

    /**
     * @param province the province to set
     */
    public void setProvince(String province) {
        this.province = province;
    }

    /**
     * @return the countyId
     */
    public Long getCountyId() {
        return countyId;
    }

    /**
     * @param countyId the countyId to set
     */
    public void setCountyId(Long countyId) {
        this.countyId = countyId;
    }
}
