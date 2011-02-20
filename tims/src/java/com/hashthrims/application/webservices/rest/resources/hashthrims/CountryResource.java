/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CountryForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.regionlist.Country;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class CountryResource {
    public abstract Collection<Country> getCountry();

    public abstract Country getCountry(Long id);

    public abstract Country getCountry(@Context Request request,RequestForm form);

    public abstract Response createCountry(@Context Request request,CountryForm form);

     public abstract Response updateCountry(@Context Request request,Country country);
}
