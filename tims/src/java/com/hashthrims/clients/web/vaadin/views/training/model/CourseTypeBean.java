/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hashthrims.clients.web.vaadin.views.training.model;

import java.util.Date;

/**
 *
 * @author boniface
 */
public class CourseTypeBean {

    private Long courseTypeId;
    private String courseTypeName;

    /**
     * @return the courseTypeId
     */
    public Long getCourseTypeId() {
        return courseTypeId;
    }

    /**
     * @param courseTypeId the courseTypeId to set
     */
    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    /**
     * @return the courseTypeName
     */
    public String getCourseTypeName() {
        return courseTypeName;
    }

    /**
     * @param courseTypeName the courseTypeName to set
     */
    public void setCourseTypeName(String courseTypeName) {
        this.courseTypeName = courseTypeName;
    }
}
