/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CompetencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.EmployeeCourses;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class CompetencyResource {
public abstract Collection<EmployeeCourses> getCompetencies();

    public abstract EmployeeCourses getCompetency(Long id);

    public abstract EmployeeCourses getCompetency(@Context Request request,RequestForm form);

    public abstract Response createCompetencies(@Context Request request,CompetencyForm form);

     public abstract Response updateCompetency(@Context Request request,EmployeeCourses competency);
}
