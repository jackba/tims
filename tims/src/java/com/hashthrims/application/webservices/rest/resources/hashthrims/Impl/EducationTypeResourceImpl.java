/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.EducationTypeResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EducationTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.employeelist.EducationType;
import com.hashthrims.services.EducationTypeService;
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
@Path("/educationTypeservice")
@Produces("application/xml")
@Service("educationTypeResource")
public class EducationTypeResourceImpl extends EducationTypeResource{

    @Autowired
    private EducationTypeService service;

    /**
     * @return the service
     */
    public EducationTypeService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(EducationTypeService service) {
        this.service = service;
    }


    @GET
    @Path("/educationTypes")
    @Override
    public Collection<EducationType> getEducationTypes(){
        return service.findAll();
    }

    @GET
    @Path("/educationType/{id}")
    @Override
    public EducationType getEducationType(@PathParam("id") Long id) {
       EducationType educationType = service.find(id);
        if (educationType == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>EducationType Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return educationType;
        }
    }

    @POST
    @Path("/educationTypes")
    @Override
    public Response createEducationType(@Context Request request, EducationTypeForm form) {
             EducationType u = new EducationType();
             u.setEduc_type_name(form.getEducationType());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/educationTypeservice/educationTypes"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/educationType")
    @Override
    public EducationType getEducationType(@Context Request request, RequestForm form) {
        try {
            EducationType educationType = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(educationType == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return educationType;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(EducationTypeResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/educationType/update")
    @Override
    public Response updateEducationType(@Context Request request, EducationType educationType) {

        if (educationType == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(educationType);
            return Response.ok(educationType).build();
        }
    }


}
