/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.SalaryGradesResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.SalaryGradesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.jobs.SalaryGrade;
import com.hashthrims.services.SalaryGradesService;
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
@Path("/salaryGradesservice")
@Produces("application/xml")
@Service("salaryGradesResource")
public class SalaryGradesResourceImpl extends SalaryGradesResource{

    @Autowired
    private SalaryGradesService service;

    /**
     * @return the service
     */
    public SalaryGradesService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(SalaryGradesService service) {
        this.service = service;
    }


    @GET
    @Path("/salaryGradess")
    @Override
    public Collection<SalaryGrade> getSalaryGradess(){
        return service.findAll();
    }

    @GET
    @Path("/salaryGrades/{id}")
    @Override
    public SalaryGrade getSalaryGrades(@PathParam("id") Long id) {
       SalaryGrade salaryGrades = service.find(id);
        if (salaryGrades == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return salaryGrades;
        }
    }

    @POST
    @Path("/salaryGradess")
    @Override
    public Response createSalaryGrades(@Context Request request, SalaryGradesForm form) {
             SalaryGrade u = new SalaryGrade();
             u.setGradeName(form.getSalaryName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/salaryGradesservice/salaryGradess"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/salaryGrades")
    @Override
    public SalaryGrade getSalaryGrades(@Context Request request, RequestForm form) {
        try {
            SalaryGrade salaryGrades = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(salaryGrades == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return salaryGrades;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(SalaryGradesResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/salaryGrades/update")
    @Override
    public Response updateSalaryGrades(@Context Request request, SalaryGrade salaryGrades) {

        if (salaryGrades == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(salaryGrades);
            return Response.ok(salaryGrades).build();
        }
    }


}
