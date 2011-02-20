/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.EmployeeBenefitsResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EmployeeBenefitsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmployeeBenefits;
import com.hashthrims.services.EmployeeBenefitsService;
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
@Path("/employeebenefitsservice")
@Produces("application/xml")
@Service("employeeBenefitsResource")
public class EmployeeBenefitsResourceImpl extends EmployeeBenefitsResource{

    @Autowired
    private EmployeeBenefitsService service;

    /**
     * @return the service
     */
    public EmployeeBenefitsService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(EmployeeBenefitsService service) {
        this.service = service;
    }


    @GET
    @Path("/employeeBenefitses")
    @Override
    public Collection<EmployeeBenefits> getEmployeeBenefitses(){
        return service.findAll();
    }

    @GET
    @Path("/employeeBenefits/{id}")
    @Override
    public EmployeeBenefits getEmployeeBenefits(@PathParam("id") Long id) {
       EmployeeBenefits competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>EmployeeBenefits Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/employeeBenefitses")
    public Response createEmployeeBenefitses(@Context Request request, EmployeeBenefitsForm form) {
             EmployeeBenefits u = new EmployeeBenefits();
//            no strings here either

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/employeebenefitsservice/employeeBenefitses"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/employeeBenefits")
    public EmployeeBenefits getEmployeeBenefits(@Context Request request, RequestForm form) {
        try {
            EmployeeBenefits competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.EmployeeBenefitsNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.EmployeeBenefitsNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/employeeBenefits/update")
    @Override
    public Response updateEmployeeBenefits(@Context Request request, EmployeeBenefits competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
