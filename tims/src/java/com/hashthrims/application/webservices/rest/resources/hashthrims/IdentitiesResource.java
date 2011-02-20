/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.Identities;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class IdentitiesResource {
public abstract Collection<Identities> getIdentitieses();

    public abstract Identities getIdentities(Long id);

    public abstract Identities getIdentities(@Context Request request,RequestForm form);

    public abstract Response createIdentitieses(@Context Request request,IdentitiesForm form);

     public abstract Response updateIdentities(@Context Request request,Identities identities);
}
