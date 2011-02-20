/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.CountryResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CountryForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.regionlist.Country;
import com.hashthrims.services.CountryService;
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
 * @author abismail
 */
@Path("/countryservice")
@Produces("application/xml")
@Service("countryResource")
public class CountryResourceImpl extends CountryResource{
    @Autowired
    private CountryService service;

    /**
     * @return the service
     */
    public CountryService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(CountryService service) {
        this.service = service;
    }

        @GET
    @Path("/countries")
    @Override
    public Collection<Country> getCountry(){
        return service.findAll();
    }

    @GET
    @Path("/country/{id}")
    @Override
    public Country getCountry(@PathParam("id") Long id) {
       Country application = service.find(id);
        if (application == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Country Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return application;
        }
    }

    @POST
    @Path("/countries")
    public Response createCountry(@Context Request request, CountryForm form) {
             Country u = new Country();
             u.setAlphaCode(form.getAlphaCode());
             u.setCountryName(form.getCountryName());
             u.setNumericCode(form.getNumericCode());
             u.setLocation(form.isLocation());
           

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/countryservice/countries"
                    + ""
                    + ""));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/country")
    public Country getCountry(@Context Request request, RequestForm form) {
        try {
            Country application = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(application == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return application;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CountryResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/country/update")
    @Override
    public Response updateCountry(@Context Request request, Country country) {

        if (country == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(country);
            return Response.ok(country).build();
        }
    }

  
}