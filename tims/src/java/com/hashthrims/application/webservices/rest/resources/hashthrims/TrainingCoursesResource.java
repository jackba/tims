/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingCoursesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingCourses;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class TrainingCoursesResource {
    public abstract Collection<TrainingCourses> getTrainingCourseses();

    public abstract TrainingCourses getTrainingCourses(Long id);

    public abstract TrainingCourses getTrainingCourses(@Context Request request,RequestForm form);

    public abstract Response createTrainingCoursess(@Context Request request,TrainingCoursesForm form);

     public abstract Response updateTrainingCourses(@Context Request request,TrainingCourses trainingCourses);
}
