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
public class JobsForm implements Serializable{
   
    private String jobTittle;
    private String jobCode;
    private String jobDesc;

    /**
     * @return the jobTittle
     */
    public String getJobTittle() {
        return jobTittle;
    }

    /**
     * @param jobTittle the jobTittle to set
     */
    public void setJobTittle(String jobTittle) {
        this.jobTittle = jobTittle;
    }

    /**
     * @return the jobCode
     */
    public String getJobCode() {
        return jobCode;
    }

    /**
     * @param jobCode the jobCode to set
     */
    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    /**
     * @return the jobDesc
     */
    public String getJobDesc() {
        return jobDesc;
    }

    /**
     * @param jobDesc the jobDesc to set
     */
    public void setJobDesc(String jobDesc) {
        this.jobDesc = jobDesc;
    }
    

   
}
