/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import java.io.*;
import javax.xml.bind.annotation.XmlRootElement;
/**
 *
 * @author administrator.dat
 */
@XmlRootElement
public class IdentitiesForm implements Serializable{
private String idType;
    private String idValue;

    /**
     * @return the idType
     */
    public String getIdType() {
        return idType;
    }

    /**
     * @param idType the idType to set
     */
    public void setIdType(String idType) {
        this.idType = idType;
    }

    /**
     * @return the idValue
     */
    public String getIdValue() {
        return idValue;
    }

    /**
     * @param idValue the idValue to set
     */
    public void setIdValue(String idValue) {
        this.idValue = idValue;
    }
}
