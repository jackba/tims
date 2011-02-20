/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
import java.util.List;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class TrainingCoursesForm implements Serializable{

    private String courseName;
    @OneToOne
    private TrainingCourseCategory courseCategory;
    private String courseTopic;
    @OneToOne
    private TrainingInstitution institutionName;

    private String conEdUnits;

    @OneToOne
    private Status courseStatus;
    private String courseNotes;
    @OneToOne
    private TrainingFunder trainingFunders;
    @OneToMany
    private List <EmployeeCourses> courseCompetencies;

    /**
     * @return the courseName
     */
    public String getCourseName() {
        return courseName;
    }

    /**
     * @param courseName the courseName to set
     */
    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    /**
     * @return the courseCategory
     */
    public TrainingCourseCategory getCourseCategory() {
        return courseCategory;
    }

    /**
     * @param courseCategory the courseCategory to set
     */
    public void setCourseCategory(TrainingCourseCategory courseCategory) {
        this.courseCategory = courseCategory;
    }

    /**
     * @return the courseTopic
     */
    public String getCourseTopic() {
        return courseTopic;
    }

    /**
     * @param courseTopic the courseTopic to set
     */
    public void setCourseTopic(String courseTopic) {
        this.courseTopic = courseTopic;
    }

    /**
     * @return the institutionName
     */
    public TrainingInstitution getInstitutionName() {
        return institutionName;
    }

    /**
     * @param institutionName the institutionName to set
     */
    public void setInstitutionName(TrainingInstitution institutionName) {
        this.institutionName = institutionName;
    }

    /**
     * @return the conEdUnits
     */
    public String getConEdUnits() {
        return conEdUnits;
    }

    /**
     * @param conEdUnits the conEdUnits to set
     */
    public void setConEdUnits(String conEdUnits) {
        this.conEdUnits = conEdUnits;
    }

    /**
     * @return the courseStatus
     */
    public Status getCourseStatus() {
        return courseStatus;
    }

    /**
     * @param courseStatus the courseStatus to set
     */
    public void setCourseStatus(Status courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * @return the courseNotes
     */
    public String getCourseNotes() {
        return courseNotes;
    }

    /**
     * @param courseNotes the courseNotes to set
     */
    public void setCourseNotes(String courseNotes) {
        this.courseNotes = courseNotes;
    }

    /**
     * @return the trainingFunders
     */
    public TrainingFunder getTrainingFunders() {
        return trainingFunders;
    }

    /**
     * @param trainingFunders the trainingFunders to set
     */
    public void setTrainingFunders(TrainingFunder trainingFunders) {
        this.trainingFunders = trainingFunders;
    }

    /**
     * @return the courseCompetencies
     */
    public List<EmployeeCourses> getCourseCompetencies() {
        return courseCompetencies;
    }

    /**
     * @param courseCompetencies the courseCompetencies to set
     */
    public void setCourseCompetencies(List<EmployeeCourses> courseCompetencies) {
        this.courseCompetencies = courseCompetencies;
    }

}
