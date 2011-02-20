/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims.Impl;

import com.hashthrims.application.webservices.rest.resources.hashthrims.EmployeeResource;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.EmployeeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.Employee;
import com.hashthrims.services.EmployeeService;
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
@Path("/employeeservice")
@Produces("application/xml")
@Service("employeeResource")
public class EmployeeResourceImpl extends EmployeeResource{

    @Autowired
    private EmployeeService service;

    /**
     * @return the service
     */
    public EmployeeService getService() {
        return service;
    }

     /**
     * @param service the service to set
     */
    public void setService(EmployeeService service) {
        this.service = service;
    }


    @GET
    @Path("/employees")
    @Override
    public Collection<Employee> getEmployees(){
        return service.findAll();
    }

    @GET
    @Path("/employee/{id}")
    @Override
    public Employee getEmployee(@PathParam("id") Long id) {
       Employee competency = service.find(id);
        if (competency == null) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Employee Not Found</error>");
            throw new WebApplicationException(builder.build());
        } else {
            return competency;
        }
    }

    @POST
    @Path("/employees")
    public Response createEmployees(@Context Request request, EmployeeForm form) {
             Employee u = new Employee();
//             there are no strings in this class either

        try {
            service.persist(u);
//            TODO: i don't know what the exact url is
            ResponseBuilder builder = Response.created(new URI("http://localhost:8084/hashthrims/ws/employeeservice/employees"));
            return builder.build();
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @POST
    @Path("/employee")
    public Employee getEmployee(@Context Request request, RequestForm form) {
        try {
            Employee competency = service.getByPropertyName(form.getPropertyName(), form.getPropertyValue());
            if(competency == null){
                throw new com.hashthrims.infrastructure.exceptions.EmployeeNotFoundException();
            }
            return competency;
        } catch (com.hashthrims.infrastructure.exceptions.EmployeeNotFoundException ex) {
            ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
            builder.type("application/xml");
            builder.entity("<error>Invalid Code</error>");
            Logger.getLogger(CompetencyResourceImpl.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(builder.build());
        }
    }

    @PUT
    @Path("/employee/update")
    @Override
    public Response updateEmployee(@Context Request request, Employee competency) {

        if (competency == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            service.merge(competency);
            return Response.ok(competency).build();
        }
    }
}
