/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.StatusResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.StatusForm;
import com.hashthrims.domain.positions.Status;
import com.hashthrims.services.StatusService;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author abismail
 */
@Path("/statusservice")
@Produces("application/xml")
@Service("statusResource")
public class StatusResourceImpl extends StatusResource{

    @Autowired
    private StatusService service;

    /**
     * @return the service
     */
    public StatusService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(StatusService service) {
        this.service = service;
    }


    @GET
    @Path("/statuses")
    @Override
    public Collection<Status> getStatuses(){
        return service.findAll();
    }

    @GET
    @Path("/status/{id}")
    @Override
    public Status getStatus(@PathParam("id") Long id) {
       Status competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Status Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/statuses")
    public Response createStatuses(@Context Request request, StatusForm form) {
             Status u = new Status();
//             TODO: this form must not have a variable status; rather, it should have a
//             buncha radiobuttons in the form from which an enum will occur and be used
//             in the form to determine the String to which the variable must be set.
//             this class will then be able to be left as is, since it'll be determined by the form
             u.setStatus(form.getStatus());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/statusservice/statuses"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/status")
    @Override
    public Status getStatus(@Context Request request, RequestForm form) {
        try {
            Status competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.StatusNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.StatusNotFoundException ex) {
            ResponseBuilder builder = Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/status/update")
    @Override
    public Response updateStatus(@Context Request request, Status competency) {

        if (competency == null) {
            return Response.status(javax.ws.rs.core.Response.Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
