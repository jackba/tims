/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.util;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.*;
/**
 *
 * @author abismail
 */
@XmlRootElement
public class ContiuingEducationCourseForm implements Serializable{

    private String nameOfContinueCourse;
    private String creditHours;

    /**
     * @return the nameOfContinueCourse
     */
    public String getNameOfContinueCourse() {
        return nameOfContinueCourse;
    }

    /**
     * @param nameOfContinueCourse the nameOfContinueCourse to set
     */
    public void setNameOfContinueCourse(String nameOfContinueCourse) {
        this.nameOfContinueCourse = nameOfContinueCourse;
    }

    /**
     * @return the creditHours
     */
    public String getCreditHours() {
        return creditHours;
    }

    /**
     * @param creditHours the creditHours to set
     */
    public void setCreditHours(String creditHours) {
        this.creditHours = creditHours;
    }

}
