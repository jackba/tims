/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.ProfessionalRegistrationResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ProfessionalRegistrationForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.ProfessionalRegistration;
import com.hashthrims.services.ProfessionalRegistrationService;
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
@Path("/professionalregistrationservice")
@Produces("application/xml")
@Service("professionalRegistrationResource")
public class ProfessionalRegistrationResourceImpl extends ProfessionalRegistrationResource{

    @Autowired
    private ProfessionalRegistrationService service;

    /**
     * @return the service
     */
    public ProfessionalRegistrationService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(ProfessionalRegistrationService service) {
        this.service = service;
    }


    @GET
    @Path("/professionalRegistrations")
    @Override
    public Collection<ProfessionalRegistration> getProfessionalRegistrations(){
        return service.findAll();
    }

    @GET
    @Path("/professionalRegistration/{id}")
    @Override
    public ProfessionalRegistration getProfessionalRegistration(@PathParam("id") Long id) {
       ProfessionalRegistration competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>ProfessionalRegistration Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/professionalRegistrations")
    @Override
    public Response createProfessionalRegistrations(@Context Request request, ProfessionalRegistrationForm form) {
             ProfessionalRegistration u = new ProfessionalRegistration();
             u.setLicenceNumber(form.getLicenceNumber());
             u.setRegistrationNumber(form.getRegistrationNumber());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/professionalregistrationservice/professionalRegistrations"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/professionalRegistration")
    public ProfessionalRegistration getProfessionalRegistration(@Context Request request, RequestForm form) {
        try {
            ProfessionalRegistration competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.ProfessionalRegistrationNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.ProfessionalRegistrationNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/professionalRegistration/update")
    @Override
    public Response updateProfessionalRegistration(@Context Request request, ProfessionalRegistration competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
