/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.MaritalStatusListResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.MaritalStatusListForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.MaritalStatusList;

import com.hashthrims.services.MaritalStatusListService;
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
@Path("/maritalStatusListservice")
@Produces("application/xml")
@Service("maritalStatusListResource")
public class MaritalStatusListResourceImpl extends MaritalStatusListResource{

    @Autowired
    private MaritalStatusListService service;

    /**
     * @return the service
     */
    public MaritalStatusListService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(MaritalStatusListService service) {
        this.service = service;
    }


    @GET
    @Path("/maritalStatusList")
    @Override
    public Collection<MaritalStatusList> getMaritalStatusLists(){
        return service.findAll();
    }

    @GET
    @Path("/maritalStatusList/{id}")
    @Override
    public MaritalStatusList getMaritalStatusList(@PathParam("id") Long id) {
       MaritalStatusList maritalStatusList = service.find(id);
        if (maritalStatusList == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>MaritalStatusList Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return maritalStatusList;
        }
    }

    @POST
    @Path("/maritalStatusLists")
    @Override
    public Response createMaritalStatusList(@Context Request request, MaritalStatusListForm form) {
             MaritalStatusList u = new MaritalStatusList();
             u.setStatus_name(form.getMaritalStatus());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/maritalStatusListservice/competencies"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/maritalStatusList")
    @Override
    public MaritalStatusList getMaritalStatusList(@Context Request request, RequestForm form) {
        try {
            MaritalStatusList maritalStatusList = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(maritalStatusList == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return maritalStatusList;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(MaritalStatusListResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/maritalStatusList/update")
    @Override
    public Response updateMaritalStatusList(@Context Request request, MaritalStatusList maritalStatusList) {

        if (maritalStatusList == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(maritalStatusList);
            return Response.ok(maritalStatusList).build();
        }
    }


}
