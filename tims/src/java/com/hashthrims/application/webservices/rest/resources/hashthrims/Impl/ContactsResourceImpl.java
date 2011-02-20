/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.ContactsResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ContactsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.Contacts;
import com.hashthrims.services.ContactsService;
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
@Path("/contactsservice")
@Produces("application/xml")
@Service("contactsResource")
public class ContactsResourceImpl extends ContactsResource{

    @Autowired
    private ContactsService service;

    /**
     * @return the service
     */
    public ContactsService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(ContactsService service) {
        this.service = service;
    }


    @GET
    @Path("/contactses")
    @Override
    public Collection<Contacts> getContactses(){
        return service.findAll();
    }

    @GET
    @Path("/contacts/{id}")
    @Override
    public Contacts getContacts(@PathParam("id") Long id) {
       Contacts competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Contacts Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/contactses")
    public Response createContactses(@Context Request request, ContactsForm form) {
             Contacts u = new Contacts();
//             u.setCompetencyName(form.getCompetencyName());
//             u.setCompetencyNotes(form.getCompetencyNotes());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/contactsservice/contactses"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/contacts")
    public Contacts getContacts(@Context Request request, RequestForm form) {
        try {
            Contacts competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.ContactsNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.ContactsNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/contacts/update")
    @Override
    public Response updateContacts(@Context Request request, Contacts competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
