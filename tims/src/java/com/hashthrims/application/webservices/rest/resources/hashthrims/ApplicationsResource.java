/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.ApplicationsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.Applications;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class ApplicationsResource {
    public abstract Collection<Applications> getApplicationses();

    public abstract Applications getApplications(Long id);

    public abstract Applications getApplications(@Context Request request,RequestForm form);

    public abstract Response createApplicationss(@Context Request request,ApplicationsForm form);

     public abstract Response updateApplications(@Context Request request,Applications applications);
}
