/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DegreeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.Degree;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class DegreeResource {
    public abstract Collection<Degree> getDegrees();

    public abstract Degree getDegree(Long id);

    public abstract Degree getDegree(@Context Request request,RequestForm form);

    public abstract Response createDegree(@Context Request request,DegreeForm form);

     public abstract Response updateDegree(@Context Request request,Degree degree);
}
