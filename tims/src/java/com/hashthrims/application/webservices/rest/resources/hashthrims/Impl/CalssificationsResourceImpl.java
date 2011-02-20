/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.ClassificationResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ClassificationForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.jobs.Classifications;
import com.hashthrims.services.ClassificationsService;
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
@Path("/classificationsservice")
@Produces("application/xml")
@Service("classificationsResource")
public class CalssificationsResourceImpl extends ClassificationResource{

    @Autowired
    private ClassificationsService service;

    /**
     * @return the service
     */
    public ClassificationsService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(ClassificationsService service) {
        this.service = service;
    }


    @GET
    @Path("/classificationss")
    @Override
    public Collection<Classifications> getClassification(){
        return service.findAll();
    }

    @GET
    @Path("/classifications/{id}")
    @Override
    public Classifications getClassification(@PathParam("id") Long id) {
       Classifications classifications = service.find(id);
        if (classifications == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return classifications;
        }
    }

    @POST
    @Path("/classificationss")
    @Override
    public Response createClassification(@Context Request request, ClassificationForm form) {
             Classifications u = new Classifications();
             u.setJob_code(form.getJobCode());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/classificationsservice/classificationss"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/classifications")
    @Override
    public Classifications getClassification(@Context Request request, RequestForm form) {
        try {
            Classifications classifications = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(classifications == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return classifications;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CalssificationsResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/classifications/update")
    @Override
    public Response updateClassification(@Context Request request, Classifications classifications) {

        if (classifications == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(classifications);
            return Response.ok(classifications).build();
        }
    }

    
    


}
