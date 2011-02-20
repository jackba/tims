/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.LanguageProficiencyResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.LanguageProficiencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.employeelist.LanguageProficiency;
import com.hashthrims.services.LangauageProficiencyService;
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
@Path("/languageProficiencyservice")
@Produces("application/xml")
@Service("languageProficiencyResource")
public class LanguageProficiencyResourceImpl extends LanguageProficiencyResource{

    @Autowired
    private LangauageProficiencyService service;

    /**
     * @return the service
     */
    public LangauageProficiencyService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(LangauageProficiencyService service) {
        this.service = service;
    }


    @GET
    @Path("/competencies")
    @Override
    public Collection<LanguageProficiency> getLanguageProficiencies(){
        return service.findAll();
    }

    @GET
    @Path("/languageProficiency/{id}")
    @Override
    public LanguageProficiency getLanguageProficiency(@PathParam("id") Long id) {
       LanguageProficiency languageProficiency = service.find(id);
        if (languageProficiency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>LanguageProficiency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return languageProficiency;
        }
    }

    @POST
    @Path("/competencies")
    public Response createLanguageProficiency(@Context Request request, LanguageProficiencyForm form) {
             LanguageProficiency u = new LanguageProficiency();
             u.setProficency(form.getLanguageProfName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/languageProficiencyservice/languageProficiencies"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/languageProficiency")
    public LanguageProficiency getLanguageProficiency(@Context Request request, RequestForm form) {
        try {
            LanguageProficiency languageProficiency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(languageProficiency == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return languageProficiency;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(LanguageProficiencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/languageProficiency/update")
    @Override
    public Response updateLanguageProficiency(@Context Request request, LanguageProficiency languageProficiency) {

        if (languageProficiency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(languageProficiency);
            return Response.ok(languageProficiency).build();
        }
    }


}
