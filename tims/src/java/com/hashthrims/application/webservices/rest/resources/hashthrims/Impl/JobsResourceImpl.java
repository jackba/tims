/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.JobsResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.JobsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.jobs.Jobs;
import com.hashthrims.services.JobsService;
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
@Path("/jobsservice")
@Produces("application/xml")
@Service("jobsResource")
public class JobsResourceImpl extends JobsResource{

    @Autowired
    private JobsService service;

    /**
     * @return the service
     */
    public JobsService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(JobsService service) {
        this.service = service;
    }


    @GET
    @Path("/jobss")
    @Override
    public Collection<Jobs> getJobss(){
        return service.findAll();
    }

    @GET
    @Path("/jobs/{id}")
    @Override
    public Jobs getJobs(@PathParam("id") Long id) {
       Jobs jobs = service.find(id);
        if (jobs == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return jobs;
        }
    }

    @POST
    @Path("/jobss")
    @Override
    public Response createJobs(@Context Request request, JobsForm form) {
             Jobs u = new Jobs();
             u.setJob_code(form.getJobCode());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/jobsservice/jobss"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/jobs")
    @Override
    public Jobs getJobs(@Context Request request, RequestForm form) {
        try {
            Jobs jobs = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(jobs == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return jobs;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(JobsResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/jobs/update")
    @Override
    public Response updateJobs(@Context Request request, Jobs jobs) {

        if (jobs == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(jobs);
            return Response.ok(jobs).build();
        }
    }


}
