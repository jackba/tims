/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingCourseEvaluationForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class TrainingCourseEvaluationResource {
    public abstract Collection<TrainingCourseEvaluation> getTrainingCourseEvaluationes();

    public abstract TrainingCourseEvaluation getTrainingCourseEvaluation(Long id);

    public abstract TrainingCourseEvaluation getTrainingCourseEvaluation(@Context Request request,RequestForm form);

    public abstract Response createTrainingCourseEvaluations(@Context Request request,TrainingCourseEvaluationForm form);

     public abstract Response updateTrainingCourseEvaluation(@Context Request request,TrainingCourseEvaluation trainingCourseEvaluation);
}
