/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.PersonResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.PersonForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.Person;
import com.hashthrims.services.PersonService;
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
@Path("/personservice")
@Produces("application/xml")
@Service("personResource")
public class PersonResourceImpl extends PersonResource{

    @Autowired
    private PersonService service;

    /**
     * @return the service
     */
    public PersonService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(PersonService service) {
        this.service = service;
    }


    @GET
    @Path("/persons")
    @Override
    public Collection<Person> getPersons(){
        return service.findAll();
    }

    @GET
    @Path("/person/{id}")
    @Override
    public Person getPerson(@PathParam("id") Long id) {
       Person competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Person Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/persons")
    public Response createPersons(@Context Request request, PersonForm form) {
             Person u = new Person();
             u.setPersonName(form.getPersonName());
           //  u.setPersonNationality(form.getPersonNationality());
             u.setPersonOtherName(form.getPersonOtherName());
             u.setPersonSurname(form.getPersonSurname());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/personservice/persons"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/person")
    public Person getPerson(@Context Request request, RequestForm form) {
        try {
            Person competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.PersonNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.PersonNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/person/update")
    @Override
    public Response updatePerson(@Context Request request, Person competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
