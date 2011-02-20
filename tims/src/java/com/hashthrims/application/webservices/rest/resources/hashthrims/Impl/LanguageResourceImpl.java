/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.LanguageResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.LanguageForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.employeelist.Language;
import com.hashthrims.services.LanguageService;
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
@Path("/languageservice")
@Produces("application/xml")
@Service("languageResource")
public class LanguageResourceImpl extends LanguageResource{

    @Autowired
    private LanguageService service;

    /**
     * @return the service
     */
    public LanguageService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(LanguageService service) {
        this.service = service;
    }


    @GET
    @Path("/competencies")
    @Override
    public Collection<Language> getLanguages(){
        return service.findAll();
    }

    @GET
    @Path("/language/{id}")
    @Override
    public Language getLanguage(@PathParam("id") Long id) {
       Language language = service.find(id);
        if (language == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Language Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return language;
        }
    }

    @POST
    @Path("/languages")
    public Response createLanguage(@Context Request request, LanguageForm form) {
             Language u = new Language();
             u.setLanguage_name(form.getLanguage());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/languageservice/languages"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/language")
    public Language getLanguage(@Context Request request, RequestForm form) {
        try {
            Language language = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(language == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return language;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(LanguageResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/language/update")
    @Override
    public Response updateLanguage(@Context Request request, Language language) {

        if (language == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(language);
            return Response.ok(language).build();
        }
    }


}
