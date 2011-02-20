/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.EducationResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EducationForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.Education;
import com.hashthrims.services.EducationService;
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
@Path("/educationservice")
@Produces("application/xml")
@Service("educationResource")
public class EducationResourceImpl extends EducationResource{

    @Autowired
    private EducationService service;

    /**
     * @return the service
     */
    public EducationService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(EducationService service) {
        this.service = service;
    }


    @GET
    @Path("/educations")
    @Override
    public Collection<Education> getEducations(){
        return service.findAll();
    }

    @GET
    @Path("/education/{id}")
    @Override
    public Education getEducation(@PathParam("id") Long id) {
       Education competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Education Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/educations")
    public Response createEducations(@Context Request request, EducationForm form) {
             Education u = new Education();
             u.setInstitution(form.getInstitution());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/educationservice/educations"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/education")
    public Education getEducation(@Context Request request, RequestForm form) {
        try {
            Education competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.EducationNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.EducationNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/education/update")
    @Override
    public Response updateEducation(@Context Request request, Education competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
