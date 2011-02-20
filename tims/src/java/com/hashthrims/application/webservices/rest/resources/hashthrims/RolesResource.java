/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.Roles;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class RolesResource {
public abstract Collection<Roles> getRoleses();

    public abstract Roles getRoles(Long id);

    public abstract Roles getRoles(@Context Request request,RequestForm form);

    public abstract Response createRoleses(@Context Request request,RolesForm form);

     public abstract Response updateRoles(@Context Request request,Roles roles);
}
