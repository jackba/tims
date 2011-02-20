/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.DistrictForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.regionlist.District;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class DistrictResource {
    public abstract Collection<District> getDistrict();

    public abstract District getDistrict(Long id);

    public abstract District getDistrict(@Context Request request,RequestForm form);

    public abstract Response createDistrict(@Context Request request,DistrictForm form);

     public abstract Response updateDistrict(@Context Request request,District district);
}
