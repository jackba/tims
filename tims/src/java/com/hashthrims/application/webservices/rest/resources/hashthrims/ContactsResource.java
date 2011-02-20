/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.hashthrims.application.webservices.rest.resources.hashthrims;

import com.hashthrims.application.webservices.rest.resources.hashthrims.util.RequestForm;
import com.hashthrims.domain.Contacts;
import java.util.Collection;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
/**
 *
 * @author administrator.dat
 */
public abstract class ContactsResource {
public abstract Collection<Contacts> getContactses();

    public abstract Contacts getContacts(Long id);

    public abstract Contacts getContacts(@Context Request request,RequestForm form);

    public abstract Response createContactses(@Context Request request,com.hashthrims.application.webservices.rest.resources.hashthrims.util.ContactsForm form);

     public abstract Response updateContacts(@Context Request request,Contacts contacts);
}
