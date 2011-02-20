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
public class LanguageProficiencyForm implements Serializable{
    
    private String languageProfName;

    /**
     * @return the languageProfName
     */
    public String getLanguageProfName() {
        return languageProfName;
    }

    /**
     * @param languageProfName the languageProfName to set
     */
    public void setLanguageProfName(String languageProfName) {
        this.languageProfName = languageProfName;
    }

   
}
