/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.SalarySourcesResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.SalarySourcesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.positions.SalarySources;
import com.hashthrims.services.SalarySourcesService;
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
@Path("/salarySourcesservice")
@Produces("application/xml")
@Service("salarySourcesResource")
public class SalarySourcesResourceImpl extends SalarySourcesResource{

    @Autowired
    private SalarySourcesService service;

    /**
     * @return the service
     */
    public SalarySourcesService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(SalarySourcesService service) {
        this.service = service;
    }


    @GET
    @Path("/salarySourcess")
    @Override
    public Collection<SalarySources> getSalarySourcess(){
        return service.findAll();
    }

    @GET
    @Path("/salarySources/{id}")
    @Override
    public SalarySources getSalarySources(@PathParam("id") Long id) {
       SalarySources salarySources = service.find(id);
        if (salarySources == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return salarySources;
        }
    }

    @POST
    @Path("/salarySourcess")
    @Override
    public Response createSalarySources(@Context Request request, SalarySourcesForm form) {
             SalarySources u = new SalarySources();
             u.setSalSourceName(form.getSalSourceName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/salarySourcesservice/salarySourcess"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/salarySources")
    @Override
    public SalarySources getSalarySources(@Context Request request, RequestForm form) {
        try {
            SalarySources salarySources = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(salarySources == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return salarySources;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(SalarySourcesResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/salarySources/update")
    @Override
    public Response updateSalarySources(@Context Request request, SalarySources salarySources) {

        if (salarySources == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(salarySources);
            return Response.ok(salarySources).build();
        }
    }


}
