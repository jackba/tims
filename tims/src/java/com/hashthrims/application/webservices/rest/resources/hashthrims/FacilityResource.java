/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.FacilityForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.offices.Facility;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class FacilityResource {
    public abstract Collection<Facility> getFacilitys();

    public abstract Facility getFacility(Long id);

    public abstract Facility getFacility(@Context Request request,RequestForm form);

    public abstract Response createFacility(@Context Request request,FacilityForm form);

     public abstract Response updateFacility(@Context Request request,Facility facility);
}
