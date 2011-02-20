/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingCourseStatusForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingCourseStatus;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class TrainingCourseStatusResource {
    public abstract Collection<TrainingCourseStatus> getTrainingCourseStatuses();

    public abstract TrainingCourseStatus getTrainingCourseStatus(Long id);

    public abstract TrainingCourseStatus getTrainingCourseStatus(@Context Request request,RequestForm form);

    public abstract Response createTrainingCourseStatuss(@Context Request request,TrainingCourseStatusForm form);

     public abstract Response updateTrainingCourseStatus(@Context Request request,TrainingCourseStatus trainingCourseStatus);
}
