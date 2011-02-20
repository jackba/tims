/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.DepartureReasonsResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DepartureReasonsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.employeelist.DepartureReasons;
import com.hashthrims.services.DepartureReasonsService;
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
@Path("/departureReasonsservice")
@Produces("application/xml")
@Service("departureReasonsResource")
public class DepartureReasonsResourceImpl extends DepartureReasonsResource{

    @Autowired
    private DepartureReasonsService service;

    /**
     * @return the service
     */
    public DepartureReasonsService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(DepartureReasonsService service) {
        this.service = service;
    }


    @GET
    @Path("/departureReasons")
    @Override
    public Collection<DepartureReasons> getDepartureReasons(){
        return service.findAll();
    }

    @GET
    @Path("/departureReason/{id}")
    @Override
    public DepartureReasons getDepartureReason(@PathParam("id") Long id) {
       DepartureReasons departureReasons = service.find(id);
        if (departureReasons == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>DepartureReasons Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return departureReasons;
        }
    }

    @POST
    @Path("/departureReasons")
    public Response createDepartureReason(@Context Request request, DepartureReasonsForm form) {
             DepartureReasons u = new DepartureReasons();
             u.setReason_name(form.getDepartureReason());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/departureReasonsservice/departureReasons"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/departureReason")
    public DepartureReasons getDepartureReason(@Context Request request, RequestForm form) {
        try {
            DepartureReasons departureReasons = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(departureReasons == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return departureReasons;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(DepartureReasonsResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/departureReason/update")
    @Override
    public Response updateDepartureReason(@Context Request request, DepartureReasons departureReasons) {

        if (departureReasons == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(departureReasons);
            return Response.ok(departureReasons).build();
        }
    }



}
