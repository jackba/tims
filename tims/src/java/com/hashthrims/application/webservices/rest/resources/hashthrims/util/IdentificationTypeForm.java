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
public class IdentificationTypeForm implements Serializable{
    
    private String identificationType;

    /**
     * @return the identificationType
     */
    public String getIdentificationType() {
        return identificationType;
    }

    /**
     * @param identificationType the identificationType to set
     */
    public void setIdentificationType(String identificationType) {
        this.identificationType = identificationType;
    }

   
}
