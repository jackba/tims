/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.domain.traininglist.TrainingInstructors;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.Date;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class ScheduledCoursesForm implements Serializable{
    
    private int numOfStuds;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startDate;
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endDate;
    private String classNotes;
    @OneToOne
    private Country classLocation;
    private String classSite;

    @OneToMany
    private List<TrainingInstructors> classInstructor;
    
    private String district;

    /**
     * @return the numOfStuds
     */
    public int getNumOfStuds() {
        return numOfStuds;
    }

    /**
     * @param numOfStuds the numOfStuds to set
     */
    public void setNumOfStuds(int numOfStuds) {
        this.numOfStuds = numOfStuds;
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
     * @return the classLocation
     */
    public Country getClassLocation() {
        return classLocation;
    }

    /**
     * @param classLocation the classLocation to set
     */
    public void setClassLocation(Country classLocation) {
        this.classLocation = classLocation;
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
     * @return the classInstructor
     */
    public List<TrainingInstructors> getClassInstructor() {
        return classInstructor;
    }

    /**
     * @param classInstructor the classInstructor to set
     */
    public void setClassInstructor(List<TrainingInstructors> classInstructor) {
        this.classInstructor = classInstructor;
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

}
