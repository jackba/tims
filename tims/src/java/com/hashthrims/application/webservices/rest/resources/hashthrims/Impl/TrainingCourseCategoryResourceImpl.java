/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.TrainingCourseCategoryResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingCourseCategoryForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.traininglist.TrainingCourseCategory;
import com.hashthrims.services.TrainingCourseCategoryService;
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
@Path("/trainingCourseCategoryservice")
@Produces("application/xml")
@Service("trainingCourseCategoryResource")
public class TrainingCourseCategoryResourceImpl extends TrainingCourseCategoryResource{

    @Autowired
    private TrainingCourseCategoryService service;

    /**
     * @return the service
     */
    public TrainingCourseCategoryService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(TrainingCourseCategoryService service) {
        this.service = service;
    }


    @GET
    @Path("/trainingCourseCategory")
    @Override
    public Collection<TrainingCourseCategory> getTrainingCourseCategoryes(){
        return service.findAll();
    }

    @GET
    @Path("/trainingCourseCategory/{id}")
    @Override
    public TrainingCourseCategory getTrainingCourseCategory(@PathParam("id") Long id) {
       TrainingCourseCategory trainingCourseCategory = service.find(id);
        if (trainingCourseCategory == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>TrainingCourseCategory Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return trainingCourseCategory;
        }
    }

    @POST
    @Path("/trainingCourseCategory")
    public Response createTrainingCourseCategorys(@Context Request request, TrainingCourseCategoryForm form) {
             TrainingCourseCategory u = new TrainingCourseCategory();
             u.setCategoryName(form.getCategoryName());



        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/trainingCourseCategoryservice/competencies"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/trainingCourseCategory")
    public TrainingCourseCategory getTrainingCourseCategory(@Context Request request, RequestForm form) {
        try {
            TrainingCourseCategory trainingCourseCategory = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(trainingCourseCategory == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return trainingCourseCategory;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(TrainingCourseCategoryResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/trainingCourseCategory/update")
    public Response updateTrainingCourseCategory(@Context Request request, TrainingCourseCategory trainingCourseCategory) {

        if (trainingCourseCategory == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(trainingCourseCategory);
            return Response.ok(trainingCourseCategory).build();
        }
    }

}
