/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.PositionTypesResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.PositionTypesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.positions.PositionTypes;
import com.hashthrims.services.PositionTypesService;
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
@Path("/positionTypesservice")
@Produces("application/xml")
@Service("positionTypesResource")
public class PositionTypeResourceImpl extends PositionTypesResource{

    @Autowired
    private PositionTypesService service;

    /**
     * @return the service
     */
    public PositionTypesService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(PositionTypesService service) {
        this.service = service;
    }


    @GET
    @Path("/positionTypess")
    @Override
    public Collection<PositionTypes> getPositionTypess(){
        return service.findAll();
    }

    @GET
    @Path("/positionTypes/{id}")
    @Override
    public PositionTypes getPositionTypes(@PathParam("id") Long id) {
       PositionTypes positionTypes = service.find(id);
        if (positionTypes == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return positionTypes;
        }
    }

    @POST
    @Path("/positionTypess")
    @Override
    public Response createPositionTypes(@Context Request request, PositionTypesForm form) {
             PositionTypes u = new PositionTypes();
             u.setPosTypeName(form.getPosTypeName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/positionTypesservice/positionTypess"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/positionTypes")
    @Override
    public PositionTypes getPositionTypes(@Context Request request, RequestForm form) {
        try {
            PositionTypes positionTypes = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(positionTypes == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return positionTypes;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(PositionTypeResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/positionTypes/update")
    @Override
    public Response updatePositionTypes(@Context Request request, PositionTypes positionTypes) {

        if (positionTypes == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(positionTypes);
            return Response.ok(positionTypes).build();
        }
    }


}
