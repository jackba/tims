/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.models;

import java.util.Date;

/**
 *
 * @author stud
 */
public class ScheduledCoursesBean {
    private String classNotes;
    private String classSite;
    private String district;
    private Date startDate;
    private Date endDate;

    /**
     * @return the classNotes
     */
    public String getClassNotes() {
        return classNotes;
    }

    /**
     * @param classNotes the classNotes to set
     */
    public void setClassNotes(String classNotes) {
        this.classNotes = classNotes;
    }

    /**
     * @return the classSite
     */
    public String getClassSite() {
        return classSite;
    }

    /**
     * @param classSite the classSite to set
     */
    public void setClassSite(String classSite) {
        this.classSite = classSite;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
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
}
