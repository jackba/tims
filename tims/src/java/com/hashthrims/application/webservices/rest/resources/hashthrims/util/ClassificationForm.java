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
public class ClassificationForm implements Serializable{
   
  private String jobName;
    private String jobDesc;
    private String jobCode;

    /**
     * @return the jobName
     */
    public String getJobName() {
        return jobName;
    }

    /**
     * @param jobName the jobName to set
     */
    public void setJobName(String jobName) {
        this.jobName = jobName;
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

   
   

   
}
