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
public class CadresForm implements Serializable{
   
    private String cadresName;

    /**
     * @return the cadresName
     */
    public String getCadresName() {
        return cadresName;
    }

    /**
     * @param cadresName the cadresName to set
     */
    public void setCadresName(String cadresName) {
        this.cadresName = cadresName;
    }

   

   
}
