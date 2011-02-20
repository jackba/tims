/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.EmployeePositionResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EmployeePositionForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmployeePosition;
import com.hashthrims.services.EmployeePositionService;
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
@Path("/employeepositionservice")
@Produces("application/xml")
@Service("employeePositionResource")
public class EmployeePositionResourceImpl extends EmployeePositionResource{

    @Autowired
    private EmployeePositionService service;

    /**
     * @return the service
     */
    public EmployeePositionService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(EmployeePositionService service) {
        this.service = service;
    }


    @GET
    @Path("/employeePositions")
    @Override
    public Collection<EmployeePosition> getEmployeePositions(){
        return service.findAll();
    }

    @GET
    @Path("/employeePosition/{id}")
    @Override
    public EmployeePosition getEmployeePosition(@PathParam("id") Long id) {
       EmployeePosition competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>EmployeePosition Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/employeePositions")
    public Response createEmployeePositions(@Context Request request, EmployeePositionForm form) {
             EmployeePosition u = new EmployeePosition();
//             there are no Strings in the domain class and hence there are none
//             in the ~From class and so there will be none here

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/employeepositionservice/employeePositions"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/employeePosition")
    @Override
    public EmployeePosition getEmployeePosition(@Context Request request, RequestForm form) {
        try {
            EmployeePosition competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.EmployeePositionNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.EmployeePositionNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/employeePosition/update")
    @Override
    public Response updateEmployeePosition(@Context Request request, EmployeePosition competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
