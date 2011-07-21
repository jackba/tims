/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * @author boniface
 */
@Entity
public class EmployeeActionPlan implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long courseId;
    private Long schduledCourse;
    private String actionPlan;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date actionPlanDate;
    private String actionPlanreview;
    private String status;
    

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
        if (!(object instanceof EmployeeActionPlan)) {
            return false;
        }
        EmployeeActionPlan other = (EmployeeActionPlan) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.hashthrims.domain.EmployeeActionPlan[ id=" + id + " ]";
    }

    /**
     * @return the courseId
     */
    public Long getCourseId() {
        return courseId;
    }

    /**
     * @param courseId the courseId to set
     */
    public void setCourseId(Long courseId) {
        this.courseId = courseId;
    }

    /**
     * @return the schduledCourse
     */
    public Long getSchduledCourse() {
        return schduledCourse;
    }

    /**
     * @param schduledCourse the schduledCourse to set
     */
    public void setSchduledCourse(Long schduledCourse) {
        this.schduledCourse = schduledCourse;
    }

    /**
     * @return the actionPlan
     */
    public String getActionPlan() {
        return actionPlan;
    }

    /**
     * @param actionPlan the actionPlan to set
     */
    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
    }

    /**
     * @return the actionPlanDate
     */
    public Date getActionPlanDate() {
        return actionPlanDate;
    }

    /**
     * @param actionPlanDate the actionPlanDate to set
     */
    public void setActionPlanDate(Date actionPlanDate) {
        this.actionPlanDate = actionPlanDate;
    }

    /**
     * @return the actionPlanreview
     */
    public String getActionPlanreview() {
        return actionPlanreview;
    }

    /**
     * @param actionPlanreview the actionPlanreview to set
     */
    public void setActionPlanreview(String actionPlanreview) {
        this.actionPlanreview = actionPlanreview;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }
    
}
