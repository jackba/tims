/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.DemographyResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DemographyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.Demography;
import com.hashthrims.services.DemographyService;
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
@Path("/demographyservice")
@Produces("application/xml")
@Service("demographyResource")
public class DemographyResourceImpl extends DemographyResource{

    @Autowired
    private DemographyService service;

    /**
     * @return the service
     */
    public DemographyService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(DemographyService service) {
        this.service = service;
    }


    @GET
    @Path("/demographies")
    @Override
    public Collection<Demography> getDemographies(){
        return service.findAll();
    }

    @GET
    @Path("/demography/{id}")
    @Override
    public Demography getDemography(@PathParam("id") Long id) {
       Demography competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Demography Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/demographies")
    public Response createDemographies(@Context Request request, DemographyForm form) {
             Demography u = new Demography();
//             u.setCompetencyName(form.getCompetencyName());
//             u.setCompetencyNotes(form.getCompetencyNotes());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/demographyservice/demographies"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/demography")
    public Demography getDemography(@Context Request request, RequestForm form) {
        try {
            Demography competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.DemographyNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.DemographyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/demography/update")
    @Override
    public Response updateDemography(@Context Request request, Demography competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
