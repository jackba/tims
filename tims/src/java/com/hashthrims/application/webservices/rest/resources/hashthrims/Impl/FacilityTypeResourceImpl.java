/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.FacilityTypeResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.FacilityTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.offices.FacilityType;
import com.hashthrims.services.FacilityTypeService;
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
@Path("/facilityTypeservice")
@Produces("application/xml")
@Service("facilityTypeResource")
public class FacilityTypeResourceImpl extends FacilityTypeResource{

    @Autowired
    private FacilityTypeService service;

    /**
     * @return the service
     */
    public FacilityTypeService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(FacilityTypeService service) {
        this.service = service;
    }


    @GET
    @Path("/facilityTypes")
    @Override
    public Collection<FacilityType> getFacilityTypes(){
        return service.findAll();
    }

    @GET
    @Path("/facilityType/{id}")
    @Override
    public FacilityType getFacilityType(@PathParam("id") Long id) {
       FacilityType facilityType = service.find(id);
        if (facilityType == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return facilityType;
        }
    }

    @POST
    @Path("/facilityTypes")
    @Override
    public Response createFacilityType(@Context Request request, FacilityTypeForm form) {
             FacilityType u = new FacilityType();
             u.setFacilityName(form.getFacilityName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/facilityTypeservice/facilityTypes"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/facilityType")
    @Override
    public FacilityType getFacilityType(@Context Request request, RequestForm form) {
        try {
            FacilityType facilityType = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(facilityType == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return facilityType;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(FacilityTypeResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/facilityType/update")
    @Override
    public Response updateFacilityType(@Context Request request, FacilityType facilityType) {

        if (facilityType == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(facilityType);
            return Response.ok(facilityType).build();
        }
    }


}
