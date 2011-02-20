/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.EmploymentHistoryResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EmploymentHistoryForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmploymentHistory;
import com.hashthrims.services.EmploymentHistoryService;
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
@Path("/employementhistoryservice")
@Produces("application/xml")
@Service("employementHistoryResource")
public class EmploymentHistoryResourceImpl extends EmploymentHistoryResource{

    @Autowired
    private EmploymentHistoryService service;

    /**
     * @return the service
     */
    public EmploymentHistoryService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(EmploymentHistoryService service) {
        this.service = service;
    }


    @GET
    @Path("/employmentHistories")
    @Override
    public Collection<EmploymentHistory> getEmploymentHistories(){
        return service.findAll();
    }

    @GET
    @Path("/employmentHistory/{id}")
    @Override
    public EmploymentHistory getEmploymentHistory(@PathParam("id") Long id) {
       EmploymentHistory competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>EmploymentHistory Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/employmentHistories")
    public Response createEmploymentHistories(@Context Request request, EmploymentHistoryForm form) {
             EmploymentHistory u = new EmploymentHistory();
             u.setCompanyAddress(form.getCompanyAddress());
             u.setCompanyName(form.getCompanyName());
             u.setCompanyTelephone(form.getCompanyTelephone());
//             u.setDateSarted(form.getDateSarted());
//             u.setEndPosition(form.getEndPosition());
             u.setEndingPosition(form.getEndingPosition());
             u.setJobResponsibilities(form.getJobResponsibilities());
             u.setReasonForLeaving(form.getReasonForLeaving());
//             u.setResponsibilities(form.getResponsibilities());
//             u.setStartPosition(form.getStartPosition());
//             u.setStartingPosition(form.getStartingPosition());
//             u.setStartingWage(form.getStartingWage());
             u.setSupervisor(form.getSupervisor());

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/employmenthistoryservice/employmentHistories"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/employmentHistory")
    public EmploymentHistory getEmploymentHistory(@Context Request request, RequestForm form) {
        try {
            EmploymentHistory competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.EmploymentHistoryNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.EmploymentHistoryNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/employmentHistory/update")
    @Override
    public Response updateEmploymentHistory(@Context Request request, EmploymentHistory competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
