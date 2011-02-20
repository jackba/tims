/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.LanguagesResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.LanguagesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmployeeLanguages;
import com.hashthrims.services.EmployeeLanguagesService;
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
@Path("/languagesservice")
@Produces("application/xml")
@Service("languagesResource")
public class LanguagesResourceImpl extends LanguagesResource{


    @Autowired
    private EmployeeLanguagesService service;

    /**
     * @return the service
     */
    public EmployeeLanguagesService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(EmployeeLanguagesService service) {
        this.service = service;
    }


    @GET
    @Path("/languageses")
    @Override
    public Collection<EmployeeLanguages> getLanguageses(){
        return service.findAll();
    }

    @GET
    @Path("/languages/{id}")
    @Override
    public EmployeeLanguages getLanguages(@PathParam("id") Long id) {
       EmployeeLanguages competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Languages Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/languageses")
    public Response createLanguageses(@Context Request request, LanguagesForm form) {
             EmployeeLanguages u = new EmployeeLanguages();
             u.setReading(form.getReading());
             u.setSpeaking(form.getSpeaking());
             u.setWriting(form.getWriting());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/languagesservice/Languageses"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/languages")
    public EmployeeLanguages getLanguages(@Context Request request, RequestForm form) {
        try {
            EmployeeLanguages competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.LanguagesNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.LanguagesNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/languages/update")
    @Override
    public Response updateLanguages(@Context Request request, EmployeeLanguages competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
