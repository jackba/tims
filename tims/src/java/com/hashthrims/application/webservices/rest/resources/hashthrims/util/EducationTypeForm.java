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
public class EducationTypeForm implements Serializable{
    
    private String educationType;

    /**
     * @return the educationType
     */
    public String getEducationType() {
        return educationType;
    }

    /**
     * @param educationType the educationType to set
     */
    public void setEducationType(String educationType) {
        this.educationType = educationType;
    }

   
}
