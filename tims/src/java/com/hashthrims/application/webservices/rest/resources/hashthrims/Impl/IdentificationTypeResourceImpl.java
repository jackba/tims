/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.IdentificationTypeResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.IdentificationTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.employeelist.IdentificationType;
import com.hashthrims.services.IdentificationTypeService;
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
@Path("/identificationTypeservice")
@Produces("application/xml")
@Service("identificationTypeResource")
public class IdentificationTypeResourceImpl extends IdentificationTypeResource{

    @Autowired
    private IdentificationTypeService service;

    /**
     * @return the service
     */
    public IdentificationTypeService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(IdentificationTypeService service) {
        this.service = service;
    }


    @GET
    @Path("/identificationType")
    @Override
    public Collection<IdentificationType> getIdentificationTypes(){
        return service.findAll();
    }

    @GET
    @Path("/identificationType/{id}")
    @Override
    public IdentificationType getIdentificationType(@PathParam("id") Long id) {
       IdentificationType identificationType = service.find(id);
        if (identificationType == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>IdentificationType Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return identificationType;
        }
    }

    @POST
    @Path("/identificationTypes")
    public Response createIdentificationType(@Context Request request, IdentificationTypeForm form) {
             IdentificationType u = new IdentificationType();
             u.setIdentity_name_type(form.getIdentificationType());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/identificationTypeservice/identificationtypes"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/identificationType")
    public IdentificationType getIdentificationType(@Context Request request, RequestForm form) {
        try {
            IdentificationType identificationType = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(identificationType == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return identificationType;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(IdentificationTypeResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/identificationType/update")
    @Override
    public Response updateIdentificationType(@Context Request request, IdentificationType identificationType) {

        if (identificationType == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(identificationType);
            return Response.ok(identificationType).build();
        }
    }


}
