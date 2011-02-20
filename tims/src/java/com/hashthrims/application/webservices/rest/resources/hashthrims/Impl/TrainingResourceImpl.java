///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;
//
//import com.hashthrims.application.webservices.rest.resources.hashthrims.TrainingResource;
//import com.hashthrims.application.webservices.rest.resources.hashthrims.util.TrainingForm;
//import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
//import com.hashthrims.domain.Training;
//import com.hashthrims.domain.traininglist.Training;
//import com.hashthrims.services.TrainingService;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.util.Collection;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.ws.rs.GET;
//import javax.ws.rs.POST;
//import javax.ws.rs.PUT;
//import javax.ws.rs.Path;
//import javax.ws.rs.PathParam;
//import javax.ws.rs.Produces;
//import javax.ws.rs.WebApplicationException;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.Request;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.Response.ResponseBuilder;
//import javax.ws.rs.core.Response.Status;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
///**
// *
// * @author administrator.dat
// */
//@Path("/trainingservice")
//@Produces("application/xml")
//@Service("trainingResource")
//public class TrainingResourceImpl extends TrainingResource{
//
//    @Autowired
//    private TrainingService service;
//
//    /**
//     * @return the service
//     */
//    public TrainingService getService() {
//        return service;
//    }
//
//     /**
//     * @param service the service to set
//     */
//    public void setService(TrainingService service) {
//        this.service = service;
//    }
//
//
//    @GET
//    @Path("/training")
//    @Override
//    public Collection<Training> getTraininges(){
//        return service.findAll();
//    }
//
//    @GET
//    @Path("/training/{id}")
//    @Override
//    public Training getTraining(@PathParam("id") Long id) {
//       Training training = service.find(id);
//        if (training == null) {
//            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
//            builder.type("application/xml");
//            builder.entity("<error>Training Not Found</error>");
//            throw new WebApplicationException(builder.build());
//        } else {
//            return training;
//        }
//    }
//
//    @POST
//    @Path("/training")
//    public Response createTrainings(@Context Request request, TrainingForm form) {
//             Training u = new Training();
//             u.setCompetency(true);
//             u.setRetraining(true);
//             u.setNotes(form.getNotes());
//             u.setSchedule(form.);
//             u.setCourseEvaluation(form.);
//             u.setCourseRequestor(form);
//
//        try {
//            service.persist(u);
////            TODO: i don't know what the exact url is
//            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/trainingservice/training"));
//            return builder.build();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @POST
//    @Path("/training")
//    public Training getTraining(@Context Request request, RequestForm form) {
//        try {
//            Training training = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
//            if(training == null){
//                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
//            }
//            return training;
//        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
//            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
//            builder.type("application/xml");
//            builder.entity("<error>Invalid Code</error>");
//            Logger.getLogger(TrainingResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            throw new WebApplicationException(builder.build());
//        }
//    }
//
//    @PUT
//    @Path("/training/update")
//    public Response updateTraining(@Context Request request, Training training) {
//
//        if (training == null) {
//            return Response.status(Status.BAD_REQUEST).build();
//        } else {
//            service.merge(training);
//            return Response.ok(training).build();
//        }
//    }
//
//}
