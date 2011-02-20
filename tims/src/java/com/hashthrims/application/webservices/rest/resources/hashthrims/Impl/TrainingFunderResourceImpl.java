/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.TrainingFunderResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingFunderForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingFunder;
import com.hashthrims.services.TrainingFunderService;
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
@Path("/trainingFunderservice")
@Produces("application/xml")
@Service("trainingFunderResource")
public class TrainingFunderResourceImpl extends TrainingFunderResource{

    @Autowired
    private TrainingFunderService service;

    /**
     * @return the service
     */
    public TrainingFunderService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(TrainingFunderService service) {
        this.service = service;
    }


    @GET
    @Path("/trainingFunder")
    @Override
    public Collection<TrainingFunder> getTrainingFunderes(){
        return service.findAll();
    }

    @GET
    @Path("/trainingFunder/{id}")
    @Override
    public TrainingFunder getTrainingFunder(@PathParam("id") Long id) {
       TrainingFunder trainingFunder = service.find(id);
        if (trainingFunder == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>TrainingFunder Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return trainingFunder;
        }
    }

    @POST
    @Path("/trainingFunder")
    public Response createTrainingFunders(@Context Request request, TrainingFunderForm form) {
             TrainingFunder u = new TrainingFunder();
            

             
        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/trainingFunderservice/trainingFunder"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/trainingFunder")
    public TrainingFunder getTrainingFunder(@Context Request request, RequestForm form) {
        try {
            TrainingFunder trainingFunder = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(trainingFunder == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return trainingFunder;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(TrainingFunderResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/trainingFunder/update")
    public Response updateTrainingFunder(@Context Request request, TrainingFunder trainingFunder) {

        if (trainingFunder == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(trainingFunder);
            return Response.ok(trainingFunder).build();
        }
    }

}
