/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.DisciplinaryActionResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DisciplinaryActionForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.DisciplinaryAction;
import com.hashthrims.infrastructure.exceptions.DisciplinaryActionNotFoundException;
import com.hashthrims.services.DisciplinaryActionService;
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
@Path("/disciplinaryactionservice")
@Produces("application/xml")
@Service("disciplinaryActionResource")
public class DisciplinaryActionResourceImpl extends DisciplinaryActionResource{
@Autowired
    private DisciplinaryActionService service;

    /**
     * @return the service
     */
    public DisciplinaryActionService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(DisciplinaryActionService service) {
        this.service = service;
    }


    @GET
    @Path("/disciplinaryActions")
    @Override
    public Collection<DisciplinaryAction> getDisciplinaryActions(){
        return service.findAll();
    }

    @GET
    @Path("/disciplinaryAction/{id}")
    @Override
    public DisciplinaryAction getDisciplinaryAction(@PathParam("id") Long id) {
       DisciplinaryAction competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>DisciplinaryAction Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/disciplinaryActions")
    @Override
    public Response createDisciplinaryActions(@Context Request request, DisciplinaryActionForm form) {
             DisciplinaryAction u = new DisciplinaryAction();
             u.setNotes(form.getNotes());
             u.setPeoplePresent(form.getPeoplePresent());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/disciplinaryactionservice/disciplinaryActions"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/disciplinaryAction")
    public DisciplinaryAction getDisciplinaryAction(@Context Request request, RequestForm form){
        try {
            DisciplinaryAction competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.DisciplinaryActionNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.DisciplinaryActionNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/disciplinaryAction/update")
    @Override
    public Response updateDisciplinaryAction(@Context Request request, DisciplinaryAction competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
