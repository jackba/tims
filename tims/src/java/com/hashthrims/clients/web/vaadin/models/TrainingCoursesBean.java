/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.clients.web.vaadin.models;

/**
 *
 * @author stud
 */
public class TrainingCoursesBean {
private String courseName;

    private String courseTopic;

//courseEducationUnits
    private String conEdUnits;

    private String courseNotes;

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
}
