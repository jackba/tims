/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.FacilityTypeForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.offices.FacilityType;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class FacilityTypeResource {
    public abstract Collection<FacilityType> getFacilityTypes();

    public abstract FacilityType getFacilityType(Long id);

    public abstract FacilityType getFacilityType(@Context Request request,RequestForm form);

    public abstract Response createFacilityType(@Context Request request,FacilityTypeForm form);

     public abstract Response updateFacilityType(@Context Request request,FacilityType facilitytype);
}
