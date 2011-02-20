/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.math.BigDecimal;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class SalaryGradesForm implements Serializable{
   
    private String salaryName;
    private String notes;
    private BigDecimal midAmount;
    private BigDecimal endAmount;
    private String startAmount;

    /**
     * @return the salaryName
     */
    public String getSalaryName() {
        return salaryName;
    }

    /**
     * @param salaryName the salaryName to set
     */
    public void setSalaryName(String salaryName) {
        this.salaryName = salaryName;
    }

    /**
     * @return the notes
     */
    public String getNotes() {
        return notes;
    }

    /**
     * @param notes the notes to set
     */
    public void setNotes(String notes) {
        this.notes = notes;
    }

    /**
     * @return the midAmount
     */
    public BigDecimal getMidAmount() {
        return midAmount;
    }

    /**
     * @param midAmount the midAmount to set
     */
    public void setMidAmount(BigDecimal midAmount) {
        this.midAmount = midAmount;
    }

    /**
     * @return the endAmount
     */
    public BigDecimal getEndAmount() {
        return endAmount;
    }

    /**
     * @param endAmount the endAmount to set
     */
    public void setEndAmount(BigDecimal endAmount) {
        this.endAmount = endAmount;
    }

    /**
     * @return the startAmount
     */
    public String getStartAmount() {
        return startAmount;
    }

    /**
     * @param startAmount the startAmount to set
     */
    public void setStartAmount(String startAmount) {
        this.startAmount = startAmount;
    }

   

   
}
