///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;
//
//import com.hashthrims.application.webservices.rest.resources.hashthrims.CityResource;
//import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CityForm;
//import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
//import com.hashthrims.domain.regionlist.City;
//import com.hashthrims.services.CityService;
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
// * @author abismail
// */
//@Path("/cityservice")
//@Produces("application/xml")
//@Service("cityResource")
//public class CityResourceImpl extends CityResource{
//    @Autowired
//    private CityService service;
//
//    /**
//     * @return the service
//     */
//    public CityService getService() {
//        return service;
//    }
//
//    /**
//     * @param service the service to set
//     */
//    public void setService(CityService service) {
//        this.service = service;
//    }
//
//        @GET
//    @Path("/cities")
//    @Override
//    public Collection<City> getCity(){
//        return service.findAll();
//    }
//
//    @GET
//    @Path("/city/{id}")
//    @Override
//    public City getCity(@PathParam("id") Long id) {
//       City application = service.find(id);
//        if (application == null) {
//            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
//            builder.type("application/xml");
//            builder.entity("<error>City Not Found</error>");
//            throw new WebApplicationException(builder.build());
//        } else {
//            return application;
//        }
//    }
//
//    @POST
//    @Path("/cities")
//    public Response createCity(@Context Request request, CityForm form) {
//             City u = new City();
//             u.setCityCode(form.getCityCode());
//              u.setName(form.getCityName());
//
//
//        try {
//            service.persist(u);
////            TODO: i don't know what the exact url is
//            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/cityservice/cities"));
//            return builder.build();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @POST
//    @Path("/city")
//    public City getCity(@Context Request request, RequestForm form) {
//        try {
//            City application = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
//            if(application == null){
//                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
//            }
//            return application;
//        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
//            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
//            builder.type("application/xml");
//            builder.entity("<error>Invalid Code</error>");
//            Logger.getLogger(CityResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            throw new WebApplicationException(builder.build());
//        }
//    }
//
//    @PUT
//    @Path("/city/update")
//    @Override
//    public Response updateCity(@Context Request request, City cities) {
//
//        if (cities == null) {
//            return Response.status(Status.BAD_REQUEST).build();
//        } else {
//            service.merge(cities);
//            return Response.ok(cities).build();
//        }
//    }
//
//
//}