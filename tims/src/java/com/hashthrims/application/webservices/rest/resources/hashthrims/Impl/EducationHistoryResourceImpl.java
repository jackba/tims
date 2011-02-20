/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.EducationHistoryResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EducationHistoryForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EducationHistory;
import com.hashthrims.services.EducationHistoryService;
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
@Path("/educationhistoryservice")
@Produces("application/xml")
@Service("educationHistoryResource")
public class EducationHistoryResourceImpl extends EducationHistoryResource{

    @Autowired
    private EducationHistoryService service;

    /**
     * @return the service
     */
    public EducationHistoryService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(EducationHistoryService service) {
        this.service = service;
    }


    @GET
    @Path("/educationHistories")
    @Override
    public Collection<EducationHistory> getEducationHistories(){
        return service.findAll();
    }

    @GET
    @Path("/educationHistory/{id}")
    @Override
    public EducationHistory getEducationHistory(@PathParam("id") Long id) {
       EducationHistory competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>EducationHistory Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/educationHistories")
    public Response createEducationHistories(@Context Request request, EducationHistoryForm form) {
             EducationHistory u = new EducationHistory();
             u.setInstituteNamwe(form.getInstituteName());
             u.setMajor(form.getMajor());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/educationhistoryservice/educationHistories"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/educationHistory")
    public EducationHistory getEducationHistory(@Context Request request, RequestForm form) {
        try {
            EducationHistory competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.EducationHistoryNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.EducationHistoryNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/educationHistory/update")
    @Override
    public Response updateEducationHistory(@Context Request request, EducationHistory competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
