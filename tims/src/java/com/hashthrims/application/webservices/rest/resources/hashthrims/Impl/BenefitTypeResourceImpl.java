/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.BenefitTypeResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.BenefitTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.BenefitType;
import com.hashthrims.services.BenefitTypeService;
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
@Path("/benefitTypeservice")
@Produces("application/xml")
@Service("benefitTypeResource")
public class BenefitTypeResourceImpl extends BenefitTypeResource{

    @Autowired
    private BenefitTypeService service;

    /**
     * @return the service
     */
    public BenefitTypeService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(BenefitTypeService service) {
        this.service = service;
    }


    @GET
    @Path("/benefitTypes")
    @Override
    public Collection<BenefitType> getBenefitType(){
        return service.findAll();
    }

    @GET
    @Path("/benefitType/{id}")
    @Override
    public BenefitType getBenefitType(@PathParam("id") Long id) {
       BenefitType benefitType = service.find(id);
        if (benefitType == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return benefitType;
        }
    }

    @POST
    @Path("/benefitTypes")
    public Response createBenefitType(@Context Request request, BenefitTypeForm form) {
             BenefitType u = new BenefitType();
             u.setBenefit_type_name(form.getBenefitType());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/benefitTypeservice/benefitTypes"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/benefitType")
    public BenefitType getBenefitType(@Context Request request, RequestForm form) {
        try {
            BenefitType benefitType = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(benefitType == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return benefitType;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(BenefitTypeResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/benefitType/update")
    @Override
    public Response updateBenefitType(@Context Request request, BenefitType benefitType) {

        if (benefitType == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(benefitType);
            return Response.ok(benefitType).build();
        }
    }




}
