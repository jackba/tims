/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.SalarySourcesForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.positions.SalarySources;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class SalarySourcesResource {
    public abstract Collection<SalarySources> getSalarySourcess();

    public abstract SalarySources getSalarySources(Long id);

    public abstract SalarySources getSalarySources(@Context Request request,RequestForm form);

    public abstract Response createSalarySources(@Context Request request,SalarySourcesForm form);

     public abstract Response updateSalarySources(@Context Request request,SalarySources salarySources);
}
