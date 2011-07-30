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
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date reviewPlanDate;
    private String actionPlanreview;
    private String status;
    private boolean review;
    private Long mentoringSessionId;
    private Long nimmartSessionId;
    

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

    /**
     * @return the review
     */
    public boolean isReview() {
        return review;
    }

    /**
     * @param review the review to set
     */
    public void setReview(boolean review) {
        this.review = review;
    }

    /**
     * @return the mentoringSessionId
     */
    public Long getMentoringSessionId() {
        return mentoringSessionId;
    }

    /**
     * @param mentoringSessionId the mentoringSessionId to set
     */
    public void setMentoringSessionId(Long mentoringSessionId) {
        this.mentoringSessionId = mentoringSessionId;
    }

    /**
     * @return the nimmartSessionId
     */
    public Long getNimmartSessionId() {
        return nimmartSessionId;
    }

    /**
     * @param nimmartSessionId the nimmartSessionId to set
     */
    public void setNimmartSessionId(Long nimmartSessionId) {
        this.nimmartSessionId = nimmartSessionId;
    }

    /**
     * @return the reviewPlanDate
     */
    public Date getReviewPlanDate() {
        return reviewPlanDate;
    }

    /**
     * @param reviewPlanDate the reviewPlanDate to set
     */
    public void setReviewPlanDate(Date reviewPlanDate) {
        this.reviewPlanDate = reviewPlanDate;
    }
    
}
