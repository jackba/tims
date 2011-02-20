
///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;
//
//import com.hashthrims.application.webservices.rest.resources.hashthrims.CompetencyResource;
//import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyForm;
//import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
//import com.hashthrims.domain.EmployeeCourses;
//import com.hashthrims.services.CompetencyService;
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
//@Path("/competencyservice")
//@Produces("application/xml")
//@Service("competencyResource")
//public class CompetencyResourceImpl extends CompetencyResource{
//
//    @Autowired
//    private CompetencyService service;
//
//    /**
//     * @return the service
//     */
//    public CompetencyService getService() {
//        return service;
//    }
//
//     /**
//     * @param service the service to set
//     */
//    public void setService(CompetencyService service) {
//        this.service = service;
//    }
//
//
//    @GET
//    @Path("/competencies")
//    @Override
//    public Collection<EmployeeCourses> getCompetencies(){
//        return service.findAll();
//    }
//
//    @GET
//    @Path("/competency/{id}")
//    @Override
//    public EmployeeCourses getCompetency(@PathParam("id") Long id) {
//       EmployeeCourses competency = service.find(id);
//        if (competency == null) {
//            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
//            builder.type("application/xml");
//            builder.entity("<error>EmployeeCourses Not Found</error>");
//            throw new WebApplicationException(builder.build());
//        } else {
//            return competency;
//        }
//    }
//
//    @POST
//    @Path("/competencies")
//    public Response createCompetencies(@Context Request request, CompetencyForm form) {
//             EmployeeCourses u = new EmployeeCourses();
//             u.setCompetencyName(form.getCompetencyName());
//             u.setCompetencyNotes(form.getCompetencyNotes());
//
//        try {
//            service.persist(u);
////            TODO: i don't know what the exact url is
//            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/competencyservice/competencies"));
//            return builder.build();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @POST
//    @Path("/competency")
//    public EmployeeCourses getCompetency(@Context Request request, RequestForm form) {
//        try {
//            EmployeeCourses competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
//            if(competency == null){
//                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
//            }
//            return competency;
//        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
//            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
//            builder.type("application/xml");
//            builder.entity("<error>Invalid Code</error>");
//            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            throw new WebApplicationException(builder.build());
//        }
//    }
//
//    @PUT
//    @Path("/competency/update")
//    public Response updateCompetency(@Context Request request, EmployeeCourses competency) {
//
//        if (competency == null) {
//            return Response.status(Status.BAD_REQUEST).build();
//        } else {
//            service.merge(competency);
//            return Response.ok(competency).build();
//        }
//    }
//}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.CompetencyResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmployeeCourses;
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
@Path("/competencyservice")
@Produces("application/xml")
@Service("competencyResource")
public class CompetencyResourceImpl extends CompetencyResource{

    @Autowired
    private CompetencyService service;

    /**
     * @return the service
     */
    public CompetencyService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(CompetencyService service) {
        this.service = service;
    }


    @GET
    @Path("/competencies")
    @Override
    public Collection<EmployeeCourses> getCompetencies(){
        return service.findAll();
    }

    @GET
    @Path("/competency/{id}")
    @Override
    public EmployeeCourses getCompetency(@PathParam("id") Long id) {
       EmployeeCourses competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/competencies")
    public Response createCompetencies(@Context Request request, CompetencyForm form) {
             EmployeeCourses u = new EmployeeCourses();
             u.setCompetencyName(form.getCompetencyName());
             u.setCompetencyNotes(form.getCompetencyNotes());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/competencyservice/competencies"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/competency")
    public EmployeeCourses getCompetency(@Context Request request, RequestForm form) {
        try {
            EmployeeCourses competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/competency/update")
    @Override
    public Response updateCompetency(@Context Request request, EmployeeCourses competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}

