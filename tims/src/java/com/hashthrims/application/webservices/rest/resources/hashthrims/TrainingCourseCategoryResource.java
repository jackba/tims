/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingCourseCategoryForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class TrainingCourseCategoryResource {
    public abstract Collection<TrainingCourseCategory> getTrainingCourseCategoryes();

    public abstract TrainingCourseCategory getTrainingCourseCategory(Long id);

    public abstract TrainingCourseCategory getTrainingCourseCategory(@Context Request request,RequestForm form);

    public abstract Response createTrainingCourseCategorys(@Context Request request,TrainingCourseCategoryForm form);

     public abstract Response updateTrainingCourseCategory(@Context Request request,TrainingCourseCategory trainingCourseCategory);
}
