/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.RegistrationBodyResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RegistrationBodyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.offices.RegistrationBody;
import com.hashthrims.services.RegistrationBodyService;
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
@Path("/registrationBodyservice")
@Produces("application/xml")
@Service("registrationBodyResource")
public class RegistrationBodyResourceImpl extends RegistrationBodyResource{

    @Autowired
    private RegistrationBodyService service;

    /**
     * @return the service
     */
    public RegistrationBodyService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(RegistrationBodyService service) {
        this.service = service;
    }


    @GET
    @Path("/registrationBodys")
    @Override
    public Collection<RegistrationBody> getRegistrationBody(){
        return service.findAll();
    }

    @GET
    @Path("/registrationBody/{id}")
    @Override
    public RegistrationBody getRegistrationBody(@PathParam("id") Long id) {
       RegistrationBody registrationBody = service.find(id);
        if (registrationBody == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return registrationBody;
        }
    }

    @POST
    @Path("/registrationBodys")
    @Override
    public Response createRegistrationBody(@Context Request request, RegistrationBodyForm form) {
             RegistrationBody u = new RegistrationBody();
             u.setName(form.getName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/registrationBodyservice/registrationBodys"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/registrationBody")
    @Override
    public RegistrationBody getRegistrationBody(@Context Request request, RequestForm form) {
        try {
            RegistrationBody registrationBody = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(registrationBody == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return registrationBody;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(RegistrationBodyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/registrationBody/update")
    @Override
    public Response updateRegistrationBody(@Context Request request, RegistrationBody registrationBody) {

        if (registrationBody == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(registrationBody);
            return Response.ok(registrationBody).build();
        }
    }


}
