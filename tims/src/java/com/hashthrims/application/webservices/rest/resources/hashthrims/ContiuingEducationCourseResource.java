/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ContiuingEducationCourseForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.ContiuingEducationCourse;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class ContiuingEducationCourseResource {
    public abstract Collection<ContiuingEducationCourse> getContiuingEducationCourses();

    public abstract ContiuingEducationCourse getContiuingEducationCourse(Long id);

    public abstract ContiuingEducationCourse getContiuingEducationCourse(@Context Request request,RequestForm form);

    public abstract Response createContiuingEducationCourses(@Context Request request,ContiuingEducationCourseForm form);

     public abstract Response updateContiuingEducationCourse(@Context Request request,ContiuingEducationCourse contiuingEducationCourse);
}
