/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import com.hashthrims.domain.*;
/**
 *
 * @author administrator.dat
 */
public abstract class UsersResource {
public abstract Collection<com.hashthrims.domain.Users> getUserss();

    public abstract Users getUsers(Long id);

    public abstract Users getUsers(@Context Request request,RequestForm form);

    public abstract Response createUserses(@Context Request request,UsersForm form);

     public abstract Response updateUsers(@Context Request request,Users users);
}
