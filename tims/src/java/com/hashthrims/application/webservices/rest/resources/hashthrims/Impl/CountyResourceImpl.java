/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.CountyResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CountyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.regionlist.County;
import com.hashthrims.services.CountyService;
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
@Path("/countyservice")
@Produces("application/xml")
@Service("countyResource")
public class CountyResourceImpl extends CountyResource{
    @Autowired
    private CountyService service;

    /**
     * @return the service
     */
    public CountyService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(CountyService service) {
        this.service = service;
    }

        @GET
    @Path("/counties")
    @Override
    public Collection<County> getCounty(){
        return service.findAll();
    }

    @GET
    @Path("/county/{id}")
    @Override
    public County getCounty(@PathParam("id") Long id) {
       County application = service.find(id);
        if (application == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>County Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return application;
        }
    }

    @POST
    @Path("/counties")
    public Response createCounty(@Context Request request, CountyForm form) {
             County u = new County();
             u.setCountyCode(form.getCountyCode());
             u.setCountyName(form.getCountyName());
            
           

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/countyservice/counties"
                    + ""
                    + ""));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/county")
    public County getCounty(@Context Request request, RequestForm form) {
        try {
            County application = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(application == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return application;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CountyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/county/update")
    @Override
    public Response updateCounty(@Context Request request, County county) {

        if (county == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(county);
            return Response.ok(county).build();
        }
    }

  
}