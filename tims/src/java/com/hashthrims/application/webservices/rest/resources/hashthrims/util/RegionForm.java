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
public class RegionForm implements Serializable{
   
   private String regionName;
    private String regionCode;

    /**
     * @return the regionName
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * @param regionName the regionName to set
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    /**
     * @return the regionCode
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * @param regionCode the regionCode to set
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode;
    }
   
   
}
