/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain;

import com.hashthrims.domain.employeelist.BenefitFrequency;
import com.hashthrims.domain.employeelist.BenefitType;
import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.domain.positions.SalarySources;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author boniface
 */
@Entity
public class EmployeeBenefits implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @OneToOne
    private BenefitType benefits;
    @OneToOne
    private SalarySources source;
    @OneToOne
    private SalaryGrade amount;
    @OneToOne
    private BenefitFrequency frequency;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;

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
        if (!(object instanceof EmployeeBenefits)) {
            return false;
        }
        EmployeeBenefits other = (EmployeeBenefits) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.Benefits[id=" + id + "]";
    }

    /**
     * @return the benefits
     */
    public BenefitType getBenefits() {
        return benefits;
    }

    /**
     * @param benefits the benefits to set
     */
    public void setBenefits(BenefitType benefits) {
        this.benefits = benefits;
    }

   
    /**
     * @return the startDate
     */
    public Date getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the endDate
     */
    public Date getEndDate() {
        return endDate;
    }

    /**
     * @param endDate the endDate to set
     */
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /**
     * @return the source
     */
    public SalarySources getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(SalarySources source) {
        this.source = source;
    }


    /**
     * @return the frequency
     */
    public BenefitFrequency getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(BenefitFrequency frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the amount
     */
    public SalaryGrade getAmount() {
        return amount;
    }

    /**
     * @param amount the amount to set
     */
    public void setAmount(SalaryGrade amount) {
        this.amount = amount;
    }

}
