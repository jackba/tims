/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.positions.model.dtos;

import java.util.Date;

/**
 *
 * @author boniface
 */
public class DateValues {

    private Date posteddate;
    private Date propoasedEndDate;
    private Date propoasedHireDate;

    /**
     * @return the posteddate
     */
    public Date getPosteddate() {
        return posteddate;
    }

    /**
     * @param posteddate the posteddate to set
     */
    public void setPosteddate(Date posteddate) {
        this.posteddate = posteddate;
    }

    /**
     * @return the propoasedEndDate
     */
    public Date getPropoasedEndDate() {
        return propoasedEndDate;
    }

    /**
     * @param propoasedEndDate the propoasedEndDate to set
     */
    public void setPropoasedEndDate(Date propoasedEndDate) {
        this.propoasedEndDate = propoasedEndDate;
    }

    /**
     * @return the propoasedHireDate
     */
    public Date getPropoasedHireDate() {
        return propoasedHireDate;
    }

    /**
     * @param propoasedHireDate the propoasedHireDate to set
     */
    public void setPropoasedHireDate(Date propoasedHireDate) {
        this.propoasedHireDate = propoasedHireDate;
    }
}
