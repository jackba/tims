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
public class AccidentTypeListForm implements Serializable{
   
    private String accidentType;

    /**
     * @return the accidentType
     */
    public String getAccidentType() {
        return accidentType;
    }

    /**
     * @param accidentType the accidentType to set
     */
    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

   
}
