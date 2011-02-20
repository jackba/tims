/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.WorkPlaceAccidentsResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.WorkPlaceAccidentsForm;
import com.hashthrims.domain.WorkPlaceAccidents;
import com.hashthrims.services.WorkPlaceAccidentsService;
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
@Path("/workplaceaccidentsservice")
@Produces("application/xml")
@Service("workPlaceAccidentsResource")
public class WorkPlaceAccidentsResourceImpl extends WorkPlaceAccidentsResource{

    @Autowired
    private WorkPlaceAccidentsService service;

    /**
     * @return the service
     */
    public WorkPlaceAccidentsService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(WorkPlaceAccidentsService service) {
        this.service = service;
    }


    @GET
    @Path("/workPlaceAccidentses")
    @Override
    public Collection<WorkPlaceAccidents> getWorkPlaceAccidentses(){
        return service.findAll();
    }

    @GET
    @Path("/workPlaceAccidents/{id}")
    @Override
    public WorkPlaceAccidents getWorkPlaceAccidents(@PathParam("id") Long id) {
       WorkPlaceAccidents competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>WorkPlaceAccidents Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/workPlaceAccidentses")
    public Response createWorkPlaceAccidentses(@Context Request request, WorkPlaceAccidentsForm form) {
             WorkPlaceAccidents u = new WorkPlaceAccidents();
             u.setFollowuopRequired(form.getFollowUpRequired());
             u.setPeopleInvioved(form.getPeopleInvolved());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/workPlaceAccidentsservice/workPlaceAccidentses"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/workPlaceAccidents")
    public WorkPlaceAccidents getWorkPlaceAccidents(@Context Request request, RequestForm form) {
        try {
            WorkPlaceAccidents competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.WorkPlaceAccidentsNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.WorkPlaceAccidentsNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/workPlaceAccidents/update")
    @Override
    public Response updateWorkPlaceAccidents(@Context Request request, WorkPlaceAccidents competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
