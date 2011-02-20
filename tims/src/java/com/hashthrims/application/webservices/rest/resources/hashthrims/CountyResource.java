/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CountyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.regionlist.County;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class CountyResource {
    public abstract Collection<County> getCounty();

    public abstract County getCounty(Long id);

    public abstract County getCounty(@Context Request request,RequestForm form);

    public abstract Response createCounty(@Context Request request,CountyForm form);

     public abstract Response updateCounty(@Context Request request,County county);
}
