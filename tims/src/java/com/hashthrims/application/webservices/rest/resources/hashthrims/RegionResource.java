/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RegionForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.regionlist.Province;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class RegionResource {
    public abstract Collection<Province> getRegion();

    public abstract Province getRegion(Long id);

    public abstract Province getRegion(@Context Request request,RequestForm form);

    public abstract Response createRegion(@Context Request request,RegionForm form);

     public abstract Response updateRegion(@Context Request request,Province region);
}
