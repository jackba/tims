/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.TrainingCoursesResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingCoursesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingCourses;
import com.hashthrims.services.TrainingCoursesService;
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
@Path("/trainingCoursesservice")
@Produces("application/xml")
@Service("trainingCoursesResource")
public class TrainingCoursesResourceImpl extends TrainingCoursesResource{

    @Autowired
    private TrainingCoursesService service;

    /**
     * @return the service
     */
    public TrainingCoursesService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(TrainingCoursesService service) {
        this.service = service;
    }


    @GET
    @Path("/trainingCourses")
    @Override
    public Collection<TrainingCourses> getTrainingCourseses(){
        return service.findAll();
    }

    @GET
    @Path("/trainingCourses/{id}")
    @Override
    public TrainingCourses getTrainingCourses(@PathParam("id") Long id) {
       TrainingCourses trainingCourses = service.find(id);
        if (trainingCourses == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>TrainingCourses Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return trainingCourses;
        }
    }

    @POST
    @Path("/trainingCourses")
    public Response createTrainingCoursess(@Context Request request, TrainingCoursesForm form) {
             TrainingCourses u = new TrainingCourses();
             
             u.setCourseCategory(form.getCourseCategory());
           
             u.setCourseName(form.getCourseName());
            
             u.setCourseStatus(form.getCourseStatus());
             u.setCourseTopic(form.getCourseTopic());
             u.setInstitutionName(form.getInstitutionName());
            

             
        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/trainingCoursesservice/trainingCourses"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/trainingCourses")
    public TrainingCourses getTrainingCourses(@Context Request request, RequestForm form) {
        try {
            TrainingCourses trainingCourses = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(trainingCourses == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return trainingCourses;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(TrainingCoursesResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/trainingCourses/update")
    public Response updateTrainingCourses(@Context Request request, TrainingCourses trainingCourses) {

        if (trainingCourses == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(trainingCourses);
            return Response.ok(trainingCourses).build();
        }
    }

}
