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
public class PositionTypesForm implements Serializable{
   
    private String posTypeName;

    /**
     * @return the posTypeName
     */
    public String getPosTypeName() {
        return posTypeName;
    }

    /**
     * @param posTypeName the posTypeName to set
     */
    public void setPosTypeName(String posTypeName) {
        this.posTypeName = posTypeName;
    }

    
}
