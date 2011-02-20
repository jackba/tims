/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.TrainingInstitutionResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingInstitutionForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingInstitution;
import com.hashthrims.services.TrainingInstitutionService;
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
@Path("/trainingInstitutionservice")
@Produces("application/xml")
@Service("trainingInstitutionResource")
public class TrainingInstitutionResourceImpl extends TrainingInstitutionResource{

    @Autowired
    private TrainingInstitutionService service;

    /**
     * @return the service
     */
    public TrainingInstitutionService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(TrainingInstitutionService service) {
        this.service = service;
    }


    @GET
    @Path("/trainingInstitution")
    @Override
    public Collection<TrainingInstitution> getTrainingInstitutiones(){
        return service.findAll();
    }

    @GET
    @Path("/trainingInstitution/{id}")
    @Override
    public TrainingInstitution getTrainingInstitution(@PathParam("id") Long id) {
       TrainingInstitution trainingInstitution = service.find(id);
        if (trainingInstitution == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>TrainingInstitution Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return trainingInstitution;
        }
    }

    @POST
    @Path("/trainingInstitution")
    public Response createTrainingInstitutions(@Context Request request, TrainingInstitutionForm form) {
             TrainingInstitution u = new TrainingInstitution();
           
             
        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/trainingInstitutionservice/trainingInstitution"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/trainingInstitution")
    public TrainingInstitution getTrainingInstitution(@Context Request request, RequestForm form) {
        try {
            TrainingInstitution trainingInstitution = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(trainingInstitution == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return trainingInstitution;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(TrainingInstitutionResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/trainingInstitution/update")
    public Response updateTrainingInstitution(@Context Request request, TrainingInstitution trainingInstitution) {

        if (trainingInstitution == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(trainingInstitution);
            return Response.ok(trainingInstitution).build();
        }
    }

}
