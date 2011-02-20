/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.JobsForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.jobs.Jobs;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class JobsResource {
    public abstract Collection<Jobs> getJobss();

    public abstract Jobs getJobs(Long id);

    public abstract Jobs getJobs(@Context Request request,RequestForm form);

    public abstract Response createJobs(@Context Request request,JobsForm form);

     public abstract Response updateJobs(@Context Request request,Jobs jobs);
}
