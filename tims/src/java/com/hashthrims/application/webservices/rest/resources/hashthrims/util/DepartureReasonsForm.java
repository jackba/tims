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
public class DepartureReasonsForm implements Serializable{
   
    private String departureReason;

    /**
     * @return the departureReason
     */
    public String getDepartureReason() {
        return departureReason;
    }

    /**
     * @param departureReason the departureReason to set
     */
    public void setDepartureReason(String departureReason) {
        this.departureReason = departureReason;
    }

     
}
