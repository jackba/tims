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
public class PersonForm implements Serializable{
private String personName;
    private String personSurname;
    private String personOtherName;
    private String personNationality;

    /**
     * @return the personName
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * @param personName the personName to set
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * @return the personSurname
     */
    public String getPersonSurname() {
        return personSurname;
    }

    /**
     * @param personSurname the personSurname to set
     */
    public void setPersonSurname(String personSurname) {
        this.personSurname = personSurname;
    }

    /**
     * @return the personOtherName
     */
    public String getPersonOtherName() {
        return personOtherName;
    }

    /**
     * @param personOtherName the personOtherName to set
     */
    public void setPersonOtherName(String personOtherName) {
        this.personOtherName = personOtherName;
    }

    /**
     * @return the personNationality
     */
    public String getPersonNationality() {
        return personNationality;
    }

    /**
     * @param personNationality the personNationality to set
     */
    public void setPersonNationality(String personNationality) {
        this.personNationality = personNationality;
    }
}
