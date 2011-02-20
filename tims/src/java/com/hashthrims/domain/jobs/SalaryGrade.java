/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain.jobs;

import com.hashthrims.domain.regionlist.Currency;
import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author boniface
 */
@Entity
public class SalaryGrade implements Serializable {

    private static long serialVersionUID = 1L;

    /**
     * @return the serialVersionUID
     */
    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    /**
     * @param aSerialVersionUID the serialVersionUID to set
     */
    public static void setSerialVersionUID(long aSerialVersionUID) {
        serialVersionUID = aSerialVersionUID;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String gradeName;
    private String notes;
    private BigDecimal midAmount;
    private BigDecimal endAmount;
    private BigDecimal startAmount;
    private BigDecimal currentAmount;
    @ManyToOne
    private Currency currency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SalaryGrade)) {
            return false;
        }
        SalaryGrade other = (SalaryGrade) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.jobs.SalaryGrades[id=" + id + "]";
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
    public Currency getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(Currency currency) {
        this.currency = currency;
    }


}
