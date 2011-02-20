/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.views.jobs.model;

import java.math.BigDecimal;

/**
 *
 * @author boniface
 */
public class SalaryGradeBean {
    private Long salaryGradeId;
    private String gradeName;
    private String notes;
    private BigDecimal midAmount;
    private BigDecimal endAmount;
    private BigDecimal startAmount;
    private BigDecimal currentAmount;
    private String currency;



    /**
     * @return the salaryGradeId
     */
    public Long getSalaryGradeId() {
        return salaryGradeId;
    }

    /**
     * @param salaryGradeId the salaryGradeId to set
     */
    public void setSalaryGradeId(Long salaryGradeId) {
        this.salaryGradeId = salaryGradeId;
    }

    /**
     * @return the gradeName
     */
    public String getGradeName() {
        return gradeName;
    }

    /**
     * @param gradeName the gradeName to set
     */
    public void setGradeName(String gradeName) {
        this.gradeName = gradeName;
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
    public BigDecimal getStartAmount() {
        return startAmount;
    }

    /**
     * @param startAmount the startAmount to set
     */
    public void setStartAmount(BigDecimal startAmount) {
        this.startAmount = startAmount;
    }

    /**
     * @return the currentAmount
     */
    public BigDecimal getCurrentAmount() {
        return currentAmount;
    }

    /**
     * @param currentAmount the currentAmount to set
     */
    public void setCurrentAmount(BigDecimal currentAmount) {
        this.currentAmount = currentAmount;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }

}
