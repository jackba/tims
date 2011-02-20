/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.TrainingCourseRequestorsResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingCourseRequestorsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingCourseRequestors;
import com.hashthrims.services.TrainingCourseRequestorsService;
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
@Path("/trainingCourseRequestorsservice")
@Produces("application/xml")
@Service("trainingCourseRequestorsResource")
public class TrainingCourseRequestorsResourceImpl extends TrainingCourseRequestorsResource{

    @Autowired
    private TrainingCourseRequestorsService service;

    /**
     * @return the service
     */
    public TrainingCourseRequestorsService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(TrainingCourseRequestorsService service) {
        this.service = service;
    }


    @GET
    @Path("/trainingCourseRequestors")
    @Override
    public Collection<TrainingCourseRequestors> getTrainingCourseRequestorses(){
        return service.findAll();
    }

    @GET
    @Path("/trainingCourseRequestors/{id}")
    @Override
    public TrainingCourseRequestors getTrainingCourseRequestors(@PathParam("id") Long id) {
       TrainingCourseRequestors trainingCourseRequestors = service.find(id);
        if (trainingCourseRequestors == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>TrainingCourseRequestors Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return trainingCourseRequestors;
        }
    }

    @POST
    @Path("/trainingCourseRequestors")
    public Response createTrainingCourseRequestorss(@Context Request request, TrainingCourseRequestorsForm form) {
             TrainingCourseRequestors u = new TrainingCourseRequestors();
             u.setRequestorName(form.getRequestorName());
             

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/trainingCourseRequestorsservice/trainingCourseRequestors"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/trainingCourseRequestors")
    public TrainingCourseRequestors getTrainingCourseRequestors(@Context Request request, RequestForm form) {
        try {
            TrainingCourseRequestors trainingCourseRequestors = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(trainingCourseRequestors == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return trainingCourseRequestors;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(TrainingCourseRequestorsResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/trainingCourseRequestors/update")
    public Response updateTrainingCourseRequestors(@Context Request request, TrainingCourseRequestors trainingCourseRequestors) {

        if (trainingCourseRequestors == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(trainingCourseRequestors);
            return Response.ok(trainingCourseRequestors).build();
        }
    }

}
