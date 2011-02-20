/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.*;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class WorkPlaceAccidentsResource {
public abstract Collection<WorkPlaceAccidents> getWorkPlaceAccidentses();

    public abstract WorkPlaceAccidents getWorkPlaceAccidents(Long id);

    public abstract WorkPlaceAccidents getWorkPlaceAccidents(@Context Request request,RequestForm form);

    public abstract Response createWorkPlaceAccidentses(@Context Request request,WorkPlaceAccidentsForm form);

     public abstract Response updateWorkPlaceAccidents(@Context Request request,WorkPlaceAccidents workPlaceAccidents);
}
