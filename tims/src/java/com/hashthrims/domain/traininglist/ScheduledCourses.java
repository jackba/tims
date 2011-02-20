/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.domain.traininglist;

import com.hashthrims.domain.regionlist.Country;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;


/**
 *
 * @author boniface
 */
@Entity
public class ScheduledCourses implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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
        if (!(object instanceof ScheduledCourses)) {
            return false;
        }
        ScheduledCourses other = (ScheduledCourses) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

//    @Override
//    public String toString() {
//        return "com.hashthrims.domain.traininglist.ScheduledCourses[id=" + id + "]";
//    }
       
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

   
    public Country getClassloc() {
        return getClassLocation();
    }

    
    public void setClassloc(Country classloc) {
        this.setClassLocation(classloc);
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

  
}
