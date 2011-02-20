/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CityForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.regionlist.City;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class CityResource {
    public abstract Collection<City> getCity();

    public abstract City getCity(Long id);

    public abstract City getCity(@Context Request request,RequestForm form);

    public abstract Response createCity(@Context Request request,CityForm form);

     public abstract Response updateCity(@Context Request request,City city);
}
