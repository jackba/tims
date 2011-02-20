/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.StatusForm;
import com.hashthrims.domain.positions.Status;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class StatusResource {
public abstract Collection<Status> getStatuses();

    public abstract Status getStatus(Long id);

    public abstract Status getStatus(@Context Request request,RequestForm form);

    public abstract Response createStatuses(@Context Request request,StatusForm form);

     public abstract Response updateStatus(@Context Request request,Status status);
}
