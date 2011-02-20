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
public class CompetencyListForm implements Serializable{
   
    private String competency;

    /**
     * @return the competency
     */
    public String getCompetency() {
        return competency;
    }

    /**
     * @param competency the competency to set
     */
    public void setCompetency(String competency) {
        this.competency = competency;
    }

   
}
