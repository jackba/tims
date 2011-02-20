/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.BenefitFrequencyResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.BenefitFrequencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.BenefitFrequency;
import com.hashthrims.services.BenefitFrequencyService;
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
@Path("/benefitFrequencyservice")
@Produces("application/xml")
@Service("benefitFrequencyResource")
public class BenefitFrequencyResourceImpl extends BenefitFrequencyResource{

    @Autowired
    private BenefitFrequencyService service;



    @GET
    @Path("/benefitFrequencies")
    @Override
    public Collection<BenefitFrequency> getBenefitFrequency(){
        return getService().findAll();
    }

    @GET
    @Path("/benefitFrequency/{id}")
    @Override
    public BenefitFrequency getBenefitFrequencies(@PathParam("id") Long id) {
       BenefitFrequency benefitFrequency = getService().find(id);
        if (benefitFrequency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>benefitFrequency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return benefitFrequency;
        }
    }

    @POST
    @Path("/benefitFrequencies")
    public Response createBenefitFrequencies(@Context Request request, BenefitFrequencyForm form) {
             BenefitFrequency u = new BenefitFrequency();
             u.setFrequency(form.getBenefitType());


        try {
            getService().persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/benefitFrequencyservice/benefitFrequencies"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/benefitFrequency")
    public BenefitFrequency getBenefitFrequencies(@Context Request request, RequestForm form) {
        try {
            BenefitFrequency benefitFrequency = getService().getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(benefitFrequency == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return benefitFrequency;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(BenefitFrequencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/benefitFrequency/update")
    @Override
    public Response updateBenefitFrequencies(@Context Request request, BenefitFrequency benefitFrequency) {

        if (benefitFrequency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            getService().merge(benefitFrequency);
            return Response.ok(benefitFrequency).build();
        }
    }

    /**
     * @return the service
     */
    public BenefitFrequencyService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(BenefitFrequencyService service) {
        this.service = service;
    }



}
