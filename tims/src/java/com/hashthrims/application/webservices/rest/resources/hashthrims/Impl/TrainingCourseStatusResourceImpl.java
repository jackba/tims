/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.TrainingCourseStatusResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingCourseStatusForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingCourseStatus;
import com.hashthrims.services.TrainingCourseStatusService;
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
@Path("/trainingCourseStatusservice")
@Produces("application/xml")
@Service("trainingCourseStatusResource")
public class TrainingCourseStatusResourceImpl extends TrainingCourseStatusResource{

    @Autowired
    private TrainingCourseStatusService service;

    /**
     * @return the service
     */
    public TrainingCourseStatusService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(TrainingCourseStatusService service) {
        this.service = service;
    }


    @GET
    @Path("/trainingCourseStatus")
    @Override
    public Collection<TrainingCourseStatus> getTrainingCourseStatuses(){
        return service.findAll();
    }

    @GET
    @Path("/trainingCourseStatus/{id}")
    @Override
    public TrainingCourseStatus getTrainingCourseStatus(@PathParam("id") Long id) {
       TrainingCourseStatus trainingCourseStatus = service.find(id);
        if (trainingCourseStatus == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>TrainingCourseStatus Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return trainingCourseStatus;
        }
    }

    @POST
    @Path("/trainingCourseStatus")
    public Response createTrainingCourseStatuss(@Context Request request, TrainingCourseStatusForm form) {
             TrainingCourseStatus u = new TrainingCourseStatus();
            u.setStatusName(form.getStatusName());
            


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/trainingCourseStatusservice/trainingCourseStatus"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/trainingCourseStatus")
    public TrainingCourseStatus getTrainingCourseStatus(@Context Request request, RequestForm form) {
        try {
            TrainingCourseStatus trainingCourseStatus = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(trainingCourseStatus == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return trainingCourseStatus;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(TrainingCourseStatusResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/trainingCourseStatus/update")
    public Response updateTrainingCourseStatus(@Context Request request, TrainingCourseStatus trainingCourseStatus) {

        if (trainingCourseStatus == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(trainingCourseStatus);
            return Response.ok(trainingCourseStatus).build();
        }
    }

}
