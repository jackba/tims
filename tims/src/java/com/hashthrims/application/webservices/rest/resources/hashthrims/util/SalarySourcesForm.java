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
public class SalarySourcesForm implements Serializable{
   
    private String SalSourceName;

    /**
     * @return the SalSourceName
     */
    public String getSalSourceName() {
        return SalSourceName;
    }

    /**
     * @param SalSourceName the SalSourceName to set
     */
    public void setSalSourceName(String SalSourceName) {
        this.SalSourceName = SalSourceName;
    }
   

    /**
     * @return the cityName
     */
   

    /**
     * @return the cityCode
     */
   


   
   
}
