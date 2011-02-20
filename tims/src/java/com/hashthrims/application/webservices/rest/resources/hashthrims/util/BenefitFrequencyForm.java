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
public class BenefitFrequencyForm implements Serializable{
    
    private String benefitFrequency;

    /**
     * @return the benefitType
     */
    public String getBenefitType() {
        return benefitFrequency;
    }

    /**
     * @param benefitType the benefitType to set
     */
    public void setBenefitType(String benefitType) {
        this.benefitFrequency = benefitType;
    }

    /**
     * @return the employmentyContract
     */
   
}
