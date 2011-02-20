/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.TrainingInstructorsResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingInstructorsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingInstructors;
import com.hashthrims.services.TrainingInstructorsService;
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
@Path("/trainingInstructorsservice")
@Produces("application/xml")
@Service("trainingInstructorsResource")
public class TrainingInstructorsResourceImpl extends TrainingInstructorsResource{

    @Autowired
    private TrainingInstructorsService service;

    /**
     * @return the service
     */
    public TrainingInstructorsService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(TrainingInstructorsService service) {
        this.service = service;
    }


    @GET
    @Path("/trainingInstructors")
    @Override
    public Collection<TrainingInstructors> getTrainingInstructorses(){
        return service.findAll();
    }

    @GET
    @Path("/trainingInstructors/{id}")
    @Override
    public TrainingInstructors getTrainingInstructors(@PathParam("id") Long id) {
       TrainingInstructors trainingInstructors = service.find(id);
        if (trainingInstructors == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>TrainingInstructors Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return trainingInstructors;
        }
    }

    @POST
    @Path("/trainingInstructors")
    public Response createTrainingInstructorss(@Context Request request, TrainingInstructorsForm form) {
             TrainingInstructors u = new TrainingInstructors();
             u.setInstructorName(form.getInstructorName());
             
        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/trainingInstructorsservice/trainingInstructors"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/trainingInstructors")
    public TrainingInstructors getTrainingInstructors(@Context Request request, RequestForm form) {
        try {
            TrainingInstructors trainingInstructors = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(trainingInstructors == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return trainingInstructors;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(TrainingInstructorsResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/trainingInstructors/update")
    public Response updateTrainingInstructors(@Context Request request, TrainingInstructors trainingInstructors) {

        if (trainingInstructors == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(trainingInstructors);
            return Response.ok(trainingInstructors).build();
        }
    }

}
