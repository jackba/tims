///*
// * To change this template, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;
//
//import com.hashthrims.application.webservices.rest.resources.hashthrims.CurrencyResource;
//import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CurrencyForm;
//import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
//import com.hashthrims.domain.regionlist.Currency;
//import com.hashthrims.services.CurrencyService;
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
//@Path("/currencyservice")
//@Produces("application/xml")
//@Service("currencyResource")
//public class CurrencyResourceImpl extends CurrencyResource{
//    @Autowired
//    private CurrencyService service;
//
//    /**
//     * @return the service
//     */
//    public CurrencyService getService() {
//        return service;
//    }
//
//    /**
//     * @param service the service to set
//     */
//    public void setService(CurrencyService service) {
//        this.service = service;
//    }
//
//        @GET
//    @Path("/counties")
//    @Override
//    public Collection<Currency> getCurrency(){
//        return service.findAll();
//    }
//
//    @GET
//    @Path("/currency/{id}")
//    @Override
//    public Currency getCurrency(@PathParam("id") Long id) {
//       Currency application = service.find(id);
//        if (application == null) {
//            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
//            builder.type("application/xml");
//            builder.entity("<error>Currency Not Found</error>");
//            throw new WebApplicationException(builder.build());
//        } else {
//            return application;
//        }
//    }
//
//    @POST
//    @Path("/counties")
//    public Response createCurrency(@Context Request request, CurrencyForm form) {
//             Currency u = new Currency();
//             u.setCurrencyCode(form.getCurrencyCode());
//             u.setCurrencyName(form.getCurrencyName());
//
//
//
//        try {
//            service.persist(u);
////            TODO: i don't know what the exact url is
//            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/currencyservice/counties"
//                    + ""
//                    + ""));
//            return builder.build();
//        } catch (URISyntaxException e) {
//            throw new RuntimeException(e);
//        }
//    }
//
//    @POST
//    @Path("/currency")
//    public Currency getCurrency(@Context Request request, RequestForm form) {
//        try {
//            Currency application = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
//            if(application == null){
//                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
//            }
//            return application;
//        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
//            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
//            builder.type("application/xml");
//            builder.entity("<error>Invalid Code</error>");
//            Logger.getLogger(CurrencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
//            throw new WebApplicationException(builder.build());
//        }
//    }
//
//    @PUT
//    @Path("/currency/update")
//    @Override
//    public Response updateCurrency(@Context Request request, Currency currency) {
//
//        if (currency == null) {
//            return Response.status(Status.BAD_REQUEST).build();
//        } else {
//            service.merge(currency);
//            return Response.ok(currency).build();
//        }
//    }
//
//
//}