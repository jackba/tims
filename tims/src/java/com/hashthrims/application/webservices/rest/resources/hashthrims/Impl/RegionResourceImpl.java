/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.RegionResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RegionForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.regionlist.Province;
import com.hashthrims.services.RegionService;
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
@Path("/regionservice")
@Produces("application/xml")
@Service("regionResource")
public class RegionResourceImpl extends RegionResource{
    @Autowired
    private RegionService service;

    /**
     * @return the service
     */
    public RegionService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(RegionService service) {
        this.service = service;
    }

        @GET
    @Path("/regions")
    @Override
    public Collection<Province> getRegion(){
        return service.findAll();
    }

    @GET
    @Path("/region/{id}")
    @Override
    public Province getRegion(@PathParam("id") Long id) {
       Province application = service.find(id);
        if (application == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Region Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return application;
        }
    }

    @POST
    @Path("/regions")
    public Response createRegion(@Context Request request, RegionForm form) {
             Province u = new Province();
             u.setRegionCode(form.getRegionCode());
             u.setRegionName(form.getRegionName());
            
           

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/regionservice/regions"
                    + ""
                    + ""));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/region")
    public Province getRegion(@Context Request request, RequestForm form) {
        try {
            Province application = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(application == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return application;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(RegionResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/region/update")
    @Override
    public Response updateRegion(@Context Request request, Province region) {

        if (region == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(region);
            return Response.ok(region).build();
        }
    }

  
}