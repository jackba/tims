/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DepartureReasonsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.employeelist.DepartureReasons;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class DepartureReasonsResource {
    public abstract Collection<DepartureReasons> getDepartureReasons();

    public abstract DepartureReasons getDepartureReason(Long id);

    public abstract DepartureReasons getDepartureReason(@Context Request request,RequestForm form);

    public abstract Response createDepartureReason(@Context Request request,DepartureReasonsForm form);

     public abstract Response updateDepartureReason(@Context Request request,DepartureReasons departureReasons);
}
