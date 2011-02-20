/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.CadresResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CadresForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.jobs.Cadres;
import com.hashthrims.services.CadresService;
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
@Path("/cadresservice")
@Produces("application/xml")
@Service("cadresResource")
public class CadresResourceImpl extends CadresResource{

    @Autowired
    private CadresService service;

    /**
     * @return the service
     */
    public CadresService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(CadresService service) {
        this.service = service;
    }


    @GET
    @Path("/cadress")
    @Override
    public Collection<Cadres> getCadres(){
        return service.findAll();
    }

    @GET
    @Path("/cadres/{id}")
    @Override
    public Cadres getCadres(@PathParam("id") Long id) {
       Cadres cadres = service.find(id);
        if (cadres == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Competency Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return cadres;
        }
    }

    @POST
    @Path("/cadress")
    @Override
    public Response createCadres(@Context Request request, CadresForm form) {
             Cadres u = new Cadres();
             u.setCadres_name(form.getCadresName());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/cadresservice/cadress"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/cadres")
    @Override
    public Cadres getCadres(@Context Request request, RequestForm form) {
        try {
            Cadres cadres = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(cadres == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return cadres;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CadresResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/cadres/update")
    @Override
    public Response updateCadres(@Context Request request, Cadres cadres) {

        if (cadres == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(cadres);
            return Response.ok(cadres).build();
        }
    }

  


}
