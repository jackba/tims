/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.FacilityResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.FacilityForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.offices.Facility;
import com.hashthrims.services.FacilityService;
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
@Path("/facilityservice")
@Produces("application/xml")
@Service("facilityResource")
public class FacilityResourceImpl extends FacilityResource{

    @Autowired
    private FacilityService service;

    /**
     * @return the service
     */
    public FacilityService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(FacilityService service) {
        this.service = service;
    }


    @GET
    @Path("/facilitys")
    @Override
    public Collection<Facility> getFacilitys(){
        return service.findAll();
    }

    @GET
    @Path("/facility/{id}")
    @Override
    public Facility getFacility(@PathParam("id") Long id) {
       Facility facility = service.find(id);
        if (facility == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return facility;
        }
    }

    @POST
    @Path("/facilitys")
    @Override
    public Response createFacility(@Context Request request, FacilityForm form) {
             Facility u = new Facility();
             u.setFacilityName(form.getFacilityName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/facilityservice/facilitys"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/facility")
    @Override
    public Facility getFacility(@Context Request request, RequestForm form) {
        try {
            Facility facility = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(facility == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return facility;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(FacilityResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/facility/update")
    @Override
    public Response updateFacility(@Context Request request, Facility facility) {

        if (facility == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(facility);
            return Response.ok(facility).build();
        }
    }


}
