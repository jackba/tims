/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ScheduledCoursesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class ScheduledCoursesResource {
    public abstract Collection<ScheduledCourses> getScheduledCourses();

    public abstract ScheduledCourses getScheduledCourses(Long id);

    public abstract ScheduledCourses getScheduledCourses(@Context Request request,RequestForm form);

    public abstract Response createScheduledCoursess(@Context Request request,ScheduledCoursesForm form);

     public abstract Response updateScheduledCourses(@Context Request request,ScheduledCourses scheduledCourses);
}
