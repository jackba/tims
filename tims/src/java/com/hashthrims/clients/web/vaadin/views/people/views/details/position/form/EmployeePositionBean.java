/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.people.views.details.position.form;

import java.util.Date;

/**
 *
 * @author boniface
 */
public class EmployeePositionBean {

    private Long id;
    private Long position;
    private Date startDate;
    private Date enddate;
    private String status;
    private Long facility;

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
     * @return the enddate
     */
    public Date getEnddate() {
        return enddate;
    }

    /**
     * @param enddate the enddate to set
     */
    public void setEnddate(Date enddate) {
        this.enddate = enddate;
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
     * @return the position
     */
    public Long getPosition() {
        return position;
    }

    /**
     * @param position the position to set
     */
    public void setPosition(Long position) {
        this.position = position;
    }

    /**
     * @return the facility
     */
    public Long getFacility() {
        return facility;
    }

    /**
     * @param facility the facility to set
     */
    public void setFacility(Long facility) {
        this.facility = facility;
    }

    
}
