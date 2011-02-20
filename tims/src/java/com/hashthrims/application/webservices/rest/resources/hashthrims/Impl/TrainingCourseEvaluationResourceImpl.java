/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.TrainingCourseEvaluationResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingCourseEvaluationForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingCourseEvaluation;
import com.hashthrims.services.TrainingCourseEvaluationService;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author administrator.dat
 */
@Path("/trainingCourseEvaluationservice")
@Produces("application/xml")
@Service("trainingCourseEvaluationResource")
public class TrainingCourseEvaluationResourceImpl extends TrainingCourseEvaluationResource{

    @Autowired
    private TrainingCourseEvaluationService service;

    /**
     * @return the service
     */
    public TrainingCourseEvaluationService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(TrainingCourseEvaluationService service) {
        this.service = service;
    }


    @GET
    @Path("/trainingCourseEvaluation")
    @Override
    public Collection<TrainingCourseEvaluation> getTrainingCourseEvaluationes(){
        return service.findAll();
    }

    @GET
    @Path("/trainingCourseEvaluation/{id}")
    @Override
    public TrainingCourseEvaluation getTrainingCourseEvaluation(@PathParam("id") Long id) {
       TrainingCourseEvaluation trainingCourseEvaluation = service.find(id);
        if (trainingCourseEvaluation == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>TrainingCourseEvaluation Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return trainingCourseEvaluation;
        }
    }

    @POST
    @Path("/trainingCourseEvaluation")
    public Response createTrainingCourseEvaluations(@Context Request request, TrainingCourseEvaluationForm form) {
             TrainingCourseEvaluation u = new TrainingCourseEvaluation();
             u.setEvaluationName(form.getEvaluationName());
             u.setCompeteEvaluation(form.getCompeteEvaluation());



        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/trainingCourseEvaluationservice/trainingCourseEvaluation"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/trainingCourseEvaluation")
    public TrainingCourseEvaluation getTrainingCourseEvaluation(@Context Request request, RequestForm form) {
        try {
            TrainingCourseEvaluation trainingCourseEvaluation = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(trainingCourseEvaluation == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return trainingCourseEvaluation;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(TrainingCourseEvaluationResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/trainingCourseEvaluation/update")
    public Response updateTrainingCourseEvaluation(@Context Request request, TrainingCourseEvaluation trainingCourseEvaluation) {

        if (trainingCourseEvaluation == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(trainingCourseEvaluation);
            return Response.ok(trainingCourseEvaluation).build();
        }
    }

}
