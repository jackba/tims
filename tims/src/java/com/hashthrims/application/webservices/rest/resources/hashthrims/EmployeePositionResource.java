/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.EmployeePosition;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class EmployeePositionResource {
public abstract Collection<EmployeePosition> getEmployeePositions();

    public abstract EmployeePosition getEmployeePosition(Long id);

    public abstract EmployeePosition getEmployeePosition(@Context Request request,RequestForm form);

    public abstract Response createEmployeePositions(@Context Request request,EmployeePositionForm form);

     public abstract Response updateEmployeePosition(@Context Request request,EmployeePosition employeePosition);
}
