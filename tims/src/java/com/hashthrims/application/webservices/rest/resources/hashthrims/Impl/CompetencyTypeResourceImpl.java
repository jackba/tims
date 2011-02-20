/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.CompetencyResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.CompetencyTypeResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.employeelist.CompetencyType;
import com.hashthrims.services.CompetencyService;
import com.hashthrims.services.CompetencyTypeService;
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
@Path("/competencyTypeservice")
@Produces("application/xml")
@Service("competencyTypeResource")
public class CompetencyTypeResourceImpl extends CompetencyTypeResource{

    @Autowired
    private CompetencyTypeService service;

    /**
     * @return the service
     */
    public CompetencyTypeService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(CompetencyTypeService service) {
        this.service = service;
    }


    @GET
    @Path("/competencyTypes")
    @Override
    public Collection<CompetencyType> getCompetencyTypes(){
        return service.findAll();
    }

    @GET
    @Path("/competencyType/{id}")
    @Override
    public CompetencyType getCompetencyType(@PathParam("id") Long id) {
       CompetencyType competencyType = service.find(id);
        if (competencyType == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competencyType;
        }
    }

    @POST
    @Path("/competencyTypes")
    @Override
    public Response createCompetencyType(@Context Request request, CompetencyTypeForm form) {
             CompetencyType u = new CompetencyType();
             u.setComp_name_typ(form.getCompetencyType());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/competencyTypeservice/competencyTypes"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/CompetencyType")
    @Override
    public CompetencyType getCompetencyType(@Context Request request, RequestForm form) {
        try {
            CompetencyType competencyType = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competencyType == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return competencyType;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyTypeResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/competencyType/update")
    @Override
    public Response updateCompetencyType(@Context Request request, CompetencyType competencyType) {

        if (competencyType == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competencyType);
            return Response.ok(competencyType).build();
        }
    }


}
