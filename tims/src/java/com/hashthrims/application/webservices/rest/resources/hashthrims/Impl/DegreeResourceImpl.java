/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.CompetencyResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.DegreeResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DegreeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.employeelist.Degree;
import com.hashthrims.services.CompetencyService;
import com.hashthrims.services.DegreeService;
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
@Path("/degreeservice")
@Produces("application/xml")
@Service("degreeResource")
public class DegreeResourceImpl extends DegreeResource{

    @Autowired
    private DegreeService service;

    /**
     * @return the service
     */
    public DegreeService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(DegreeService service) {
        this.service = service;
    }


    @GET
    @Path("/degrees")
    @Override
    public Collection<Degree> getDegrees(){
        return service.findAll();
    }

    @GET
    @Path("/degree/{id}")
    @Override
    public Degree getDegree(@PathParam("id") Long id) {
       Degree degree = service.find(id);
        if (degree == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return degree;
        }
    }

    @POST
    @Path("/degrees")
    @Override
    public Response createDegree(@Context Request request, DegreeForm form) {
             Degree u = new Degree();
             u.setDegree_name(form.getDegreeName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/degreeservice/degrees"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/degree")
    @Override
    public Degree getDegree(@Context Request request, RequestForm form) {
        try {
            Degree degree = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(degree == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return degree;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(DegreeResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/degree/update")
    @Override
    public Response updateDegree(@Context Request request, Degree degree) {

        if (degree == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(degree);
            return Response.ok(degree).build();
        }
    }


}
