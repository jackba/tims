/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.CompetencyEvaluationResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.CompetencyResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyEvaluationForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmployeeCourses;
import com.hashthrims.domain.employeelist.CompetencyEvaluation;
import com.hashthrims.services.CompetencyEvaluationService;
import com.hashthrims.services.CompetencyService;
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
@Path("/competencyEvaluationservice")
@Produces("application/xml")
@Service("competencyEvaluationResource")
public class CompetencyEvaluationResourceImpl extends CompetencyEvaluationResource{

    @Autowired
    private CompetencyEvaluationService service;

    /**
     * @return the service
     */
    public CompetencyEvaluationService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(CompetencyEvaluationService service) {
        this.service = service;
    }


    @GET
    @Path("/competencieEvaluations")
    @Override
    public Collection<CompetencyEvaluation> getCompetencyEvaluations(){
        return service.findAll();
    }

    @GET
    @Path("/competency/{id}")
    @Override
    public CompetencyEvaluation getCompetencyEvaluation(@PathParam("id") Long id) {
       CompetencyEvaluation competencyEvaluation = service.find(id);
        if (competencyEvaluation == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competencyEvaluation;
        }
    }

    @POST
    @Path("/competencyEvaluation")
    public Response createCompetencyEvaluation(@Context Request request, CompetencyEvaluationForm form) {
             CompetencyEvaluation u = new CompetencyEvaluation();
             u.setCompt_type_name(form.getCompetencyEvaluationName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/competencyEvaluationservice/competencyEvaluations"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/competencyEvaluation")
    public CompetencyEvaluation getCompetencyEvaluation(@Context Request request, RequestForm form) {
        try {
            CompetencyEvaluation competencyEvaluation = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competencyEvaluation == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return competencyEvaluation;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyEvaluationResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/competencyEvaluation/update")
    @Override
    public Response updateCompetencyEvaluation(@Context Request request, CompetencyEvaluation competencyEvaluation) {

        if (competencyEvaluation == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competencyEvaluation);
            return Response.ok(competencyEvaluation).build();
        }
    }




}
