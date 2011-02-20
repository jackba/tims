/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.Employee;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class EmployeeResource {
public abstract Collection<Employee> getEmployees();

    public abstract Employee getEmployee(Long id);

    public abstract Employee getEmployee(@Context Request request,RequestForm form);

    public abstract Response createEmployees(@Context Request request,EmployeeForm form);

     public abstract Response updateEmployee(@Context Request request,Employee employee);
}
