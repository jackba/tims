/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.*;
import com.hashthrims.domain.Person;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class PersonResource {
public abstract Collection<Person> getPersons();

    public abstract Person getPerson(Long id);

    public abstract Person getPerson(@Context Request request,RequestForm form);

    public abstract Response createPersons(@Context Request request,PersonForm form);

     public abstract Response updatePerson(@Context Request request,Person person);
}
