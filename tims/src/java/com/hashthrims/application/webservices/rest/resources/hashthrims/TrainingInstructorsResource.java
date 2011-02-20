/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingInstructorsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingInstructors;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class TrainingInstructorsResource {
    public abstract Collection<TrainingInstructors> getTrainingInstructorses();

    public abstract TrainingInstructors getTrainingInstructors(Long id);

    public abstract TrainingInstructors getTrainingInstructors(@Context Request request,RequestForm form);

    public abstract Response createTrainingInstructorss(@Context Request request,TrainingInstructorsForm form);

     public abstract Response updateTrainingInstructors(@Context Request request,TrainingInstructors trainingInstructors);
}
