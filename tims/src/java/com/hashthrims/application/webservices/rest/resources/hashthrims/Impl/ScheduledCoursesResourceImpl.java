/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.ScheduledCoursesResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ScheduledCoursesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
//import com.hashthrims.domain.ScheduledCourses;
import com.hashthrims.domain.traininglist.ScheduledCourses;
import com.hashthrims.services.ScheduledCoursesService;
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
@Path("/scheduledCoursesservice")
@Produces("application/xml")
@Service("scheduledCoursesResource")
public class ScheduledCoursesResourceImpl extends ScheduledCoursesResource{

    @Autowired
    private ScheduledCoursesService service;

    /**
     * @return the service
     */
    public ScheduledCoursesService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(ScheduledCoursesService service) {
        this.service = service;
    }


    @GET
    @Path("/competencies")
    @Override
    public Collection<ScheduledCourses> getScheduledCourses(){
        return service.findAll();
    }

    @GET
    @Path("/scheduledCourses/{id}")
    @Override
    public ScheduledCourses getScheduledCourses(@PathParam("id") Long id) {
       ScheduledCourses scheduledCourses = service.find(id);
        if (scheduledCourses == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>ScheduledCourses Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return scheduledCourses;
        }
    }

    @POST
    @Path("/competencies")
    public Response createScheduledCoursess(@Context Request request, ScheduledCoursesForm form) {
             ScheduledCourses u = new ScheduledCourses();
             u.setNumOfStuds(form.getNumOfStuds());
             u.setStartDate(form.getStartDate());
             u.setEndDate(form.getEndDate());
             u.setClassInstructor(form.getClassInstructor());
             u.setClassLocation(form.getClassLocation());
             u.setClassNotes(form.getClassNotes());
             u.setClassSite(form.getClassSite());
             u.setDistrict(form.getDistrict());



        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/scheduledCoursesservice/competencies"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/scheduledCourses")
    public ScheduledCourses getScheduledCourses(@Context Request request, RequestForm form) {
        try {
            ScheduledCourses scheduledCourses = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(scheduledCourses == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return scheduledCourses;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(ScheduledCoursesResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/scheduledCourses/update")
    public Response updateScheduledCourses(@Context Request request, ScheduledCourses scheduledCourses) {

        if (scheduledCourses == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(scheduledCourses);
            return Response.ok(scheduledCourses).build();
        }
    }


}
