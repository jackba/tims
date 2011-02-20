/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.DistrictResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DistrictForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.regionlist.District;
import com.hashthrims.services.DistrictService;
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
@Path("/districtservice")
@Produces("application/xml")
@Service("districtResource")
public class DistrictResourceImpl extends DistrictResource{
    @Autowired
    private DistrictService service;

    /**
     * @return the service
     */
    public DistrictService getService() {
        return service;
    }

    /**
     * @param service the service to set
     */
    public void setService(DistrictService service) {
        this.service = service;
    }

        @GET
    @Path("/districts")
    @Override
    public Collection<District> getDistrict(){
        return service.findAll();
    }

    @GET
    @Path("/district/{id}")
    @Override
    public District getDistrict(@PathParam("id") Long id) {
       District application = service.find(id);
        if (application == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>District Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return application;
        }
    }

    @POST
    @Path("/districts")
    public Response createDistrict(@Context Request request, DistrictForm form) {
             District u = new District();
             u.setDistrictCode(form.getDistrictCode());
             u.setDistrictName(form.getDistrictName());
            
           

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/districtservice/districts"
                    + ""
                    + ""));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/district")
    public District getDistrict(@Context Request request, RequestForm form) {
        try {
            District application = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(application == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return application;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(DistrictResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/district/update")
    @Override
    public Response updateDistrict(@Context Request request, District district) {

        if (district == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(district);
            return Response.ok(district).build();
        }
    }

  
}