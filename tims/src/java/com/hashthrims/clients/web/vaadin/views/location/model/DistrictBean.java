/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.location.model;

/**
 *
 * @author boniface
 */
public class DistrictBean {
    private String districtName;
    private String districtCode;
    private String county;
    private Long districtId;

    /**
     * @return the districtName
     */
    public String getDistrictName() {
        return districtName;
    }

    /**
     * @param districtName the districtName to set
     */
    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    /**
     * @return the districtCode
     */
    public String getDistrictCode() {
        return districtCode;
    }

    /**
     * @param districtCode the districtCode to set
     */
    public void setDistrictCode(String districtCode) {
        this.districtCode = districtCode;
    }

    /**
     * @return the county
     */
    public String getCounty() {
        return county;
    }

    /**
     * @param county the county to set
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * @return the districtId
     */
    public Long getDistrictId() {
        return districtId;
    }

    /**
     * @param districtId the districtId to set
     */
    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

}
