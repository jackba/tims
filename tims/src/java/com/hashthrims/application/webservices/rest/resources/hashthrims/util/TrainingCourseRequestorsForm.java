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
public class TrainingCourseRequestorsForm implements Serializable{

    private String requestorName;

    /**
     * @return the requestorName
     */
    public String getRequestorName() {
        return requestorName;
    }

    /**
     * @param requestorName the requestorName to set
     */
    public void setRequestorName(String requestorName) {
        this.requestorName = requestorName;
    }

}
