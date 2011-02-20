/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.Demography;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class DemographyResource {
public abstract Collection<Demography> getDemographies();

    public abstract Demography getDemography(Long id);

    public abstract Demography getDemography(@Context Request request,RequestForm form);

    public abstract Response createDemographies(@Context Request request,DemographyForm form);

     public abstract Response updateDemography(@Context Request request,Demography demography);
}
