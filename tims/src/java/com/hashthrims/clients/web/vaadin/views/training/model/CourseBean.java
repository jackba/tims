/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.model;

import java.util.List;

/**
 *
 * @author boniface
 */
public class CourseBean {

    private Long courseId;
    private String courseName;
    private String courseTopic;
    private String courseType;
    private String courseCatergory;
    private String trainingInstitution;
    private String courseStatus;
    private List<String> trainingFunder;
    private List<String> competency;
    private List<String> targetGroup;
    private String criteria;
    private String courseNotes;

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
     * @return the courseType
     */
    public String getCourseType() {
        return courseType;
    }

    /**
     * @param courseType the courseType to set
     */
    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    /**
     * @return the courseCatergory
     */
    public String getCourseCatergory() {
        return courseCatergory;
    }

    /**
     * @param courseCatergory the courseCatergory to set
     */
    public void setCourseCatergory(String courseCatergory) {
        this.courseCatergory = courseCatergory;
    }

    /**
     * @return the trainingInstitution
     */
    public String getTrainingInstitution() {
        return trainingInstitution;
    }

    /**
     * @param trainingInstitution the trainingInstitution to set
     */
    public void setTrainingInstitution(String trainingInstitution) {
        this.trainingInstitution = trainingInstitution;
    }

    /**
     * @return the courseStatus
     */
    public String getCourseStatus() {
        return courseStatus;
    }

    /**
     * @param courseStatus the courseStatus to set
     */
    public void setCourseStatus(String courseStatus) {
        this.courseStatus = courseStatus;
    }

    /**
     * @return the trainingFunder
     */
    public List<String> getTrainingFunder() {
        return trainingFunder;
    }

    /**
     * @param trainingFunder the trainingFunder to set
     */
    public void setTrainingFunder(List<String> trainingFunder) {
        this.trainingFunder = trainingFunder;
    }

    /**
     * @return the competency
     */
    public List<String> getCompetency() {
        return competency;
    }

    /**
     * @param competency the competency to set
     */
    public void setCompetency(List<String> competency) {
        this.competency = competency;
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
     * @return the targetGroup
     */
    public List<String> getTargetGroup() {
        return targetGroup;
    }

    /**
     * @param targetGroup the targetGroup to set
     */
    public void setTargetGroup(List<String> targetGroup) {
        this.targetGroup = targetGroup;
    }

    /**
     * @return the criteria
     */
    public String getCriteria() {
        return criteria;
    }

    /**
     * @param criteria the criteria to set
     */
    public void setCriteria(String criteria) {
        this.criteria = criteria;
    }
}
