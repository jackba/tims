/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.managetraining.windows.actionplans.model;

import java.util.Date;

/**
 *
 * @author boniface
 */
public class ActionPlanBean {
    private Long id;
    private String actionPlan;
    private Date actionPlanDate;
    private Date reviewDate;
    private String actionPlanreview;
    private String status;

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return the reviewDate
     */
    public Date getReviewDate() {
        return reviewDate;
    }

    /**
     * @param reviewDate the reviewDate to set
     */
    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
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
}
