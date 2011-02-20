/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.DisciplineActionTypeListResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DisciplineActionTypeListForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.employeelist.DisciplineActionTypeList;
import com.hashthrims.services.DisciplineActionTypeListService;
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
@Path("/disciplineActionTypeListservice")
@Produces("application/xml")
@Service("disciplineActionTypeListResource")
public class DisciplineActionListResourceImpl extends DisciplineActionTypeListResource{

    @Autowired
    private DisciplineActionTypeListService service;

    /**
     * @return the service
     */
    public DisciplineActionTypeListService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(DisciplineActionTypeListService service) {
        this.service = service;
    }


    @GET
    @Path("/disciplineActionTypeLists")
    @Override
    public Collection<DisciplineActionTypeList> getDisciplineActionTypeLists(){
        return service.findAll();
    }

    @GET
    @Path("/disciplineActionTypeList/{id}")
    @Override
    public DisciplineActionTypeList getDisciplineActionTypeList(@PathParam("id") Long id) {
       DisciplineActionTypeList disciplineActionTypeList = service.find(id);
        if (disciplineActionTypeList == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>DisciplineActionTypeList Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return disciplineActionTypeList;
        }
    }

    @POST
    @Path("/disciplineActionTypeLists")
    @Override
    public Response createDisciplineActionTypeList(@Context Request request, DisciplineActionTypeListForm form) {
             DisciplineActionTypeList u = new DisciplineActionTypeList();
             u.setDisplineName(form.getDisciplineAction());


        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/disciplineActionTypeListservice/disciplineActionLists"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/disciplineActionTypeList")
    @Override
    public DisciplineActionTypeList getDisciplineActionTypeList(@Context Request request, RequestForm form) {
        try {
            DisciplineActionTypeList disciplineActionTypeList = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(disciplineActionTypeList == null){
                throw new com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException();
            }
            return disciplineActionTypeList;
        } catch (com.hashthrims.infrastructure.exceptions.CompetencyNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(DisciplineActionListResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/disciplineActionTypeList/update")
    @Override
    public Response updateDisciplineActionTypeList(@Context Request request, DisciplineActionTypeList disciplineActionTypeList) {

        if (disciplineActionTypeList == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(disciplineActionTypeList);
            return Response.ok(disciplineActionTypeList).build();
        }
    }



}
