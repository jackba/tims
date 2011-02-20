/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;


import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CadresForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.jobs.Cadres;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class CadresResource {
    public abstract Collection<Cadres> getCadres();

    public abstract Cadres getCadres(Long id);

    public abstract Cadres getCadres(@Context Request request,RequestForm form);

    public abstract Response createCadres(@Context Request request,CadresForm form);

     public abstract Response updateCadres(@Context Request request,Cadres cadres);
}
