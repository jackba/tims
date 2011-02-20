/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.IdentitiesResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.IdentitiesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.Identities;
import com.hashthrims.services.IdentitiesService;
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
@Path("/identitiesservice")
@Produces("application/xml")
@Service("identitiesResource")
public class IdentitiesResourceImpl extends IdentitiesResource{

@Autowired
    private IdentitiesService service;

    /**
     * @return the service
     */
    public IdentitiesService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(IdentitiesService service) {
        this.service = service;
    }


    @GET
    @Path("/identitieses")
    @Override
    public Collection<Identities> getIdentitieses(){
        return service.findAll();
    }

    @GET
    @Path("/identities/{id}")
    @Override
    public Identities getIdentities(@PathParam("id") Long id) {
       Identities competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Identities Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/identitieses")
    public Response createIdentitieses(@Context Request request, IdentitiesForm form) {
             Identities u = new Identities();
             u.setIdType(form.getIdType());
             u.setIdValue(form.getIdValue());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/identitiesservice/Identitieses"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/identities")
    public Identities getIdentities(@Context Request request, RequestForm form) {
        try {
            Identities competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.IdentitiesNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.IdentitiesNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/identities/update")
    @Override
    public Response updateIdentities(@Context Request request, Identities competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
