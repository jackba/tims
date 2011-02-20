/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import com.hashthrims.domain.Contacts;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.List;
import javax.persistence.OneToMany;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class TrainingFunderForm implements Serializable{

    private String nameTrainer;
    private String coutry;
    private String district;
    @OneToMany
    private List <Contacts> contactInfo;

    /**
     * @return the nameTrainer
     */
    public String getNameTrainer() {
        return nameTrainer;
    }

    /**
     * @param nameTrainer the nameTrainer to set
     */
    public void setNameTrainer(String nameTrainer) {
        this.nameTrainer = nameTrainer;
    }

    /**
     * @return the 
     */
    public String getCoutry() {
        return coutry;
    }

    /**
     * @param coutry the  to set
     */
    public void setCoutry(String coutry) {
        this.coutry = coutry;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the contactInfo
     */
    public List<Contacts> getContactInfo() {
        return contactInfo;
    }

    /**
     * @param contactInfo the contactInfo to set
     */
    public void setContactInfo(List<Contacts> contactInfo) {
        this.contactInfo = contactInfo;
    }
}
