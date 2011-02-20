/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.ApplicationsResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ApplicationsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.Applications;
import com.hashthrims.domain.Applications;
import com.hashthrims.services.ApplicationService;
import com.hashthrims.services.ApplicationsService;
import com.vaadin.terminal.ApplicationResource;
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
 * @author abismail
 */
@Path("/applicationservice")
@Produces("application/xml")
@Service("applicationResource")
public class ApplicationResourceImpl extends ApplicationsResource{
    @Autowired
    private ApplicationsService service;

    /**
     * @return the service
     */
    public ApplicationsService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(ApplicationsService service) {
        this.service = service;
    }

        @GET
    @Path("/applicationses")
    @Override
    public Collection<Applications> getApplicationses(){
        return service.findAll();
    }

    @GET
    @Path("/application/{id}")
    @Override
    public Applications getApplications(@PathParam("id") Long id) {
       Applications application = service.find(id);
        if (application == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Applications Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return application;
        }
    }

    @POST
    @Path("/applicationses")
    public Response createApplicationss(@Context Request request, ApplicationsForm form) {
             Applications u = new Applications();
             u.setAdditionalSkills(form.getAdditionalSkills());
             u.setAdverSource(form.getAdverSource());
             u.setAvailability(form.getAvailability());
             u.setFelony(form.getFelony());
             u.setFelonyDetails(form.getFelonyDetails());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/applicationservice/applicationses"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/application")
    public Applications getApplications(@Context Request request, RequestForm form) {
        try {
            Applications application = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(application == null){
                throw new com.hashthrims.infrastructure.exceptions.ApplicationsNotFoundException();
            }
            return application;
        } catch (com.hashthrims.infrastructure.exceptions.ApplicationsNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(ApplicationResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/application/update")
    @Override
    public Response updateApplications(@Context Request request, Applications application) {

        if (application == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(application);
            return Response.ok(application).build();
        }
    }
}