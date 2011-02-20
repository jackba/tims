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
public class BenefitTypeForm implements Serializable{
   
    private String benefitType;

    /**
     * @return the benefitType
     */
    public String getBenefitType() {
        return benefitType;
    }

    /**
     * @param benefitType the benefitType to set
     */
    public void setBenefitType(String benefitType) {
        this.benefitType = benefitType;
    }

   
}
