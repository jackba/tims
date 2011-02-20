/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.EmployeeBenefits;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class EmployeeBenefitsResource {
public abstract Collection<EmployeeBenefits> getEmployeeBenefitses();

    public abstract EmployeeBenefits getEmployeeBenefits(Long id);

    public abstract EmployeeBenefits getEmployeeBenefits(@Context Request request,RequestForm form);

    public abstract Response createEmployeeBenefitses(@Context Request request,EmployeeBenefitsForm form);

     public abstract Response updateEmployeeBenefits(@Context Request request,EmployeeBenefits employeeBenefits);
}
