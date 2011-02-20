/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.CurrencyForm;
import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;

import com.hashthrims.domain.regionlist.Currency;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author abismail
 */
public abstract class CurrencyResource {
    public abstract Collection<Currency> getCurrency();

    public abstract Currency getCurrency(Long id);

    public abstract Currency getCurrency(@Context Request request,RequestForm form);

    public abstract Response createCurrency(@Context Request request,CurrencyForm form);

     public abstract Response updateCurrency(@Context Request request,Currency currency);
}
