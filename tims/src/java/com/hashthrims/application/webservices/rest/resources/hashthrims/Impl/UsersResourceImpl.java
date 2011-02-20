/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.UsersResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.UsersForm;
import com.hashthrims.domain.Users;
import com.hashthrims.services.UsersService;
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
@Path("/usersservice")
@Produces("application/xml")
@Service("usersResource")
public class UsersResourceImpl extends UsersResource{

    @Autowired
    private UsersService service;

    /**
     * @return the service
     */
    public UsersService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(UsersService service) {
        this.service = service;
    }


    @GET
    @Path("/userses")
    @Override
    public Collection<Users> getUserss(){
        return service.findAll();
    }

    @GET
    @Path("/users/{id}")
    @Override
    public Users getUsers(@PathParam("id") Long id) {
       Users competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Users Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/userss")
    @Override
    public Response createUserses(@Context Request request, UsersForm form) {
             Users u = new Users();
             u.setFirstname(form.getFirstname());
             u.setLastname(form.getLastname());
             u.setMiddlename(form.getMiddlename());
             u.setPasswd(form.getPasswd());
            

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/usersservice/userss"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/users")
    @Override
    public Users getUsers(@Context Request request, RequestForm form) {
        try {
            Users competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.UsersNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.UsersNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/users/update")
    @Override
    public Response updateUsers(@Context Request request, Users competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
